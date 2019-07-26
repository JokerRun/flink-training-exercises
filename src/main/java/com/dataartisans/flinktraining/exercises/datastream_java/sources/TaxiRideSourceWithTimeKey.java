/*
 * Copyright 2015 data Artisans GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dataartisans.flinktraining.exercises.datastream_java.sources;

import com.dataartisans.flinktraining.exercises.datastream_java.datatypes.TaxiRide;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.api.watermark.Watermark;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.GZIPInputStream;

/**
 * This SourceFunction generates a data stream of TaxiRide records which are
 * read from a gzipped input file. Each record has a time stamp and the input file must be
 * ordered by this time stamp.
 * <p>
 * In order to simulate a realistic stream source, the SourceFunction serves events proportional to
 * their timestamps. In addition, the serving of events can be delayed by a bounded random delay
 * which causes the events to be served slightly out-of-order of their timestamps.
 * <p>
 * The serving speed of the SourceFunction can be adjusted by a serving speed factor.
 * A factor of 60.0 increases the logical serving time by a factor of 60, i.e., events of one
 * minute (60 seconds) are served in 1 second.
 * <p>
 * This SourceFunction is an EventSourceFunction and does continuously emit watermarks.
 * Hence it is able to operate in event time mode which is configured as follows:
 * <p>
 * StreamExecutionEnvironment.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
 */
public class TaxiRideSourceWithTimeKey implements SourceFunction<TaxiRide> {

    private final int maxDelayMsecs;
    private final int watermarkDelayMSecs;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
    private final String dataFilePath;
    private final int servingSpeed;

    private transient BufferedReader reader;
    private transient InputStream gzipStream;

    /**
     * Serves the TaxiRide records from the specified and ordered gzipped input file.
     * Rides are served exactly in order of their time stamps
     * at the speed at which they were originally generated.
     *
     * @param dataFilePath The gzipped input file from which the TaxiRide records are read.
     */
    public TaxiRideSourceWithTimeKey(String dataFilePath) {
        this(dataFilePath, 0, 1);
    }

    /**
     * Serves the TaxiRide records from the specified and ordered gzipped input file.
     * Rides are served exactly in order of their time stamps
     * in a serving speed which is proportional to the specified serving speed factor.
     *
     * @param dataFilePath       The gzipped input file from which the TaxiRide records are read.
     * @param servingSpeedFactor The serving speed factor by which the logical serving time is adjusted.
     */
    public TaxiRideSourceWithTimeKey(String dataFilePath, int servingSpeedFactor) {
        this(dataFilePath, 0, servingSpeedFactor);
    }

    /**
     * Serves the TaxiRide records from the specified and ordered gzipped input file.
     * Rides are served out-of time stamp order with specified maximum random delay
     * in a serving speed which is proportional to the specified serving speed factor.
     *
     * @param dataFilePath       The gzipped input file from which the TaxiRide records are read.
     * @param maxEventDelaySecs  The max time in seconds by which events are delayed.
     * @param servingSpeedFactor The serving speed factor by which the logical serving time is adjusted.
     */
    public TaxiRideSourceWithTimeKey(String dataFilePath, int maxEventDelaySecs, int servingSpeedFactor) {
        if (maxEventDelaySecs < 0) {
            throw new IllegalArgumentException("Max event delay must be positive");
        }
        this.dataFilePath = dataFilePath;
        this.maxDelayMsecs = maxEventDelaySecs * 1000;
        this.watermarkDelayMSecs = maxDelayMsecs < 10000 ? 10000 : maxDelayMsecs;
        this.servingSpeed = servingSpeedFactor;
    }

    @Override
    public void run(SourceContext<TaxiRide> sourceContext) throws Exception {

        gzipStream = new GZIPInputStream(new FileInputStream(dataFilePath));
        reader = new BufferedReader(new InputStreamReader(gzipStream, "UTF-8"));

        generateUnorderedStream(sourceContext);

        this.reader.close();
        this.reader = null;
        this.gzipStream.close();
        this.gzipStream = null;

    }

    private void generateUnorderedStream(SourceContext<TaxiRide> sourceContext) throws Exception {


        Date servingStartTime = new Date(Calendar.getInstance().getTimeInMillis());
        Date dataStartTime;

        Random rand = new Random(7452);
        //发送队列，该队列会自动对其中的元素按照【计划发送时间】进行排序
        PriorityQueue<Tuple2<String, Object>> emitSchedule = new PriorityQueue<>(32, (Tuple2<String, Object> o1, Tuple2<String, Object> o2) -> {
            return o1.f0.compareTo(o2.f0);
        });

        // read first ride and insert it into emit schedule
        String line;
        TaxiRide ride;
        if (reader.ready() && (line = reader.readLine()) != null) {
            // read first ride
            ride = TaxiRide.fromString(line);
            // extract starting timestamp
            dataStartTime = new Date(getEventTime(ride));//dataStartTime代表第一条时间的EventTime时间节点，即数据流起点
            // get delayed time
            Date delayedEventTime = new Date(dataStartTime.getTime() + getNormalDelayMsecs(rand));

            emitSchedule.add(new Tuple2<String, Object>(sdf.format(delayedEventTime), ride));//第一条schedule为的Key为数据流起始时间节点+数据延迟到达的时间节点，Value为该Ride事件
            // schedule next watermark
            Date watermarkTime = new Date(dataStartTime.getTime() + watermarkDelayMSecs);
            Watermark nextWatermark = new Watermark(watermarkTime.getTime() - maxDelayMsecs - 1);// dataStartTime -1
            emitSchedule.add(new Tuple2<String, Object>(sdf.format(watermarkTime), nextWatermark));//第二条schedule为 {数据流起始时间节点+最大延迟时间 --> Watermark对象（起始时间节点-1)}
            //第一个WaterMark会在数据流开始后的【最大延迟时间】后到达，并告知FlinkApplication从此刻起不再接受【起始时间节点】之前的所有数据。(如果还有，则会被路由到【SideOutput旁路输出】)
        } else {
            return;
        }

        // peek at next ride
//        if (reader.ready() && (line = reader.readLine()) != null) {
//            ride = TaxiRide.fromString(line);
//        }

        // read rides one-by-one and emit a random ride from the buffer each time
        while (emitSchedule.size() > 0 || reader.ready()) {

            // insert all events into schedule that might be emitted next
            // 添加队列前，声明判断条件需要的参数
            Date curNextDelayedEventTime = !emitSchedule.isEmpty() ? sdf.parse(emitSchedule.peek().f0) : new Date(-1);
            Date rideEventTime = new Date(ride != null ? getEventTime(ride) : -1);

            while (ride != null && ( /* while there is a ride AND*/ emitSchedule.isEmpty() || /* and no ride in schedule OR*/ rideEventTime.getTime() < curNextDelayedEventTime.getTime() + maxDelayMsecs) /* not enough rides in schedule*/)
            //当peek出来的第二行记录不为空，且【事件时间】<emitSchedule中的第一个事件的【计划到达时间】+最大延迟，则进入循环,
            // 当无法继续该循环，意味着一个周期(最大延迟)批次数据已经全都被加入到【发送队列】中
            {
                // insert event into emit schedule //为循环中的事件安排【计划到达时间】,并将该事件添加到发送队列
                Date delayedEventTime = new Date(rideEventTime.getTime() + getNormalDelayMsecs(rand));
                emitSchedule.add(new Tuple2<String, Object>(sdf.format(delayedEventTime), ride));

                // read next ride
                if (reader.ready() && (line = reader.readLine()) != null) {
                    ride = TaxiRide.fromString(line);
                    rideEventTime = new Date(getEventTime(ride));
                } else {
                    ride = null;
                    rideEventTime = new Date(-1);
                }
            }

            // emit schedule is updated, emit next element in schedule
            // 取出首个事件准备放入sourceContext作为数据源发送给FilnkApplication
            Tuple2<String, Object> head = emitSchedule.poll();

            Date delayedEventTime = sdf.parse(head.f0);

            long now = Calendar.getInstance().getTimeInMillis();
            //【服务时间】= 当前时间 + (数据开始事件-计划到达时间)/服务速度)
            long servingTime = toServingTime(servingStartTime.getTime(), dataStartTime.getTime(), delayedEventTime.getTime());
            long waitTime = servingTime - now;
            //等待时间=服务时间-当前时间
            Thread.sleep((waitTime > 0) ? waitTime : 0);

            if (head.f1 instanceof TaxiRide) {
                TaxiRide emitRide = (TaxiRide) head.f1;
                // emit ride
                sourceContext.collectWithTimestamp(emitRide, getEventTime(emitRide));
            } else if (head.f1 instanceof Watermark) { //如果取出来的是WaterMark，则发送该wm并往队列中添加下一个wm
                Watermark emitWatermark = (Watermark) head.f1;
                // emit watermark
                sourceContext.emitWatermark(emitWatermark);
                // schedule next watermark
                long watermarkTime = delayedEventTime.getTime() + watermarkDelayMSecs;
                Watermark nextWatermark = new Watermark(watermarkTime - maxDelayMsecs - 1);
                emitSchedule.add(new Tuple2<String, Object>(sdf.format(new Date(watermarkTime)), nextWatermark));
            }
        }

    }

    public long toServingTime(long servingStartTime, long dataStartTime, long eventTime) {
        long dataDiff = eventTime - dataStartTime;
        return servingStartTime + (dataDiff / this.servingSpeed);
    }

    public long getEventTime(TaxiRide ride) {
        return ride.getEventTime();
    }

    public long getNormalDelayMsecs(Random rand) {
        long delay = -1;
        long x = maxDelayMsecs / 2;
        while (delay < 0 || delay > maxDelayMsecs) {//调整Delay事件，直到Delay<最大延迟事件
            delay = (long) (rand.nextGaussian() * x) + x;
        }
        return delay;
    }

    @Override
    public void cancel() {
        try {
            if (this.reader != null) {
                this.reader.close();
            }
            if (this.gzipStream != null) {
                this.gzipStream.close();
            }
        } catch (IOException ioe) {
            throw new RuntimeException("Could not cancel SourceFunction", ioe);
        } finally {
            this.reader = null;
            this.gzipStream = null;
        }
    }

}

