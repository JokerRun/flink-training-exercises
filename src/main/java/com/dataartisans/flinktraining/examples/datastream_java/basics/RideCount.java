/*
 * Copyright 2018 data Artisans GmbH
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

package com.dataartisans.flinktraining.examples.datastream_java.basics;

import com.dataartisans.flinktraining.exercises.datastream_java.datatypes.TaxiRide;
import com.dataartisans.flinktraining.exercises.datastream_java.sources.TaxiRideSource;
import com.dataartisans.flinktraining.exercises.datastream_java.sources.TaxiRideSourceWithTimeKey;
import com.dataartisans.flinktraining.exercises.datastream_java.utils.ExerciseBase;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * Example that counts the rides for each driver.
 *
 * Parameters:
 *   -input path-to-input-file
 *
 * 	Note that this is implicitly keeping state for each driver.
 * 	This sort of simple, non-windowed aggregation on an unbounded set of keys will use an unbounded amount of state.
 * 	When this is an issue, look at the SQL/Table API, or ProcessFunction, or state TTL, all of which provide
 * 	mechanisms for expiring state for stale keys.
 */
public class RideCount {
	public static void main(String[] args) throws Exception {

		ParameterTool params = ParameterTool.fromArgs(args);
		final String input = params.get("input", ExerciseBase.pathToRideData);

		final int maxEventDelay = 60;       // events are out of order by max 60 seconds
		final int servingSpeedFactor = 600; // events of 10 minutes are served every second

		// set up streaming execution environment
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

		// start the data generator
		DataStream<TaxiRide> rides = env.addSource(new TaxiRideSourceWithTimeKey(input, maxEventDelay, servingSpeedFactor));

		// map each ride to a tuple of (driverId, 1)
		DataStream<Tuple2<Long, Long>> tuples = rides.map(new MapFunction<TaxiRide, Tuple2<Long, Long>>() {
					@Override
					public Tuple2<Long, Long> map(TaxiRide ride) throws Exception {
						return new Tuple2<Long, Long>(ride.driverId, 1L) ;
					}
		});
//        tuples.print();
		// partition the stream by the driverId
		KeyedStream<Tuple2<Long, Long>, Tuple> keyedByDriverId = tuples.keyBy(0);
//		keyedByDriverId.print();

		// count the rides for each driver
		DataStream<Tuple2<Long, Long>> rideCounts = keyedByDriverId.sum(1);

		// we could, in fact, print out any or all of these streams
		rideCounts.print();

		// run the cleansing pipeline
		env.execute("Ride Count");
	}
}
