# [ 1.Home](https://ci.apache.org/projects/flink/flink-docs-release-1.8/)

------

#  2.Concepts

## [Programming Model](https://ci.apache.org/projects/flink/flink-docs-release-1.8/concepts/programming-model.html)
## [Distributed Runtime](https://ci.apache.org/projects/flink/flink-docs-release-1.8/concepts/runtime.html)

#  3.Tutorials

## API Tutorials
### [DataStream API](https://ci.apache.org/projects/flink/flink-docs-release-1.8/tutorials/datastream_api.html)
## Setup Tutorials
### [Local Setup](https://ci.apache.org/projects/flink/flink-docs-release-1.8/tutorials/local_setup.html)
### [Running Flink on Windows](https://ci.apache.org/projects/flink/flink-docs-release-1.8/tutorials/flink_on_windows.html)

#  4.Examples

## [Overview](https://ci.apache.org/projects/flink/flink-docs-release-1.8/examples/)
## [Batch Examples](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/batch/examples.html)

------

#  5.Application Development

## 5.1.Project Build Setup

### [Project Template for Java](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/projectsetup/java_api_quickstart.html)
### [Project Template for Scala](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/projectsetup/scala_api_quickstart.html)
### [Configuring Dependencies, Connectors, Libraries](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/projectsetup/dependencies.html)
## 5.2.Basic API Concepts

### [Overview](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/api_concepts.html)
### [Scala API Extensions](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/scala_api_extensions.html)
### [Java Lambda Expressions](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/java_lambdas.html)
## 5.3.Streaming (DataStream API)

### [Overview](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/datastream_api.html)
### Event Time
#### [Overview](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/event_time.html)
#### [Generating Timestamps / Watermarks](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/event_timestamps_watermarks.html)
#### [Pre-defined Timestamp Extractors / Watermark Emitters](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/event_timestamp_extractors.html)
### State & Fault Tolerance
#### [Overview](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/stream/state/)
#### [Working with State](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/stream/state/state.html)
#### [The Broadcast State Pattern](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/stream/state/broadcast_state.html)
#### [Checkpointing](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/stream/state/checkpointing.html)
#### [Queryable State](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/stream/state/queryable_state.html)
#### [State Backends](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/stream/state/state_backends.html)
#### [State Schema Evolution](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/stream/state/schema_evolution.html)
#### [Custom State Serialization](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/stream/state/custom_serialization.html)
### Operators
#### [Overview](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/stream/operators/)
#### [Windows](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/stream/operators/windows.html)
#### [Joining](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/stream/operators/joining.html)
#### [Process Function](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/stream/operators/process_function.html)
#### [Async I/O](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/stream/operators/asyncio.html)
### Connectors
#### [Overview](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/connectors/)
#### [Fault Tolerance Guarantees](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/connectors/guarantees.html)
#### [Kafka](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/connectors/kafka.html)
#### [Cassandra](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/connectors/cassandra.html)
#### [Kinesis](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/connectors/kinesis.html)
#### [Elasticsearch](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/connectors/elasticsearch.html)
#### [Rolling File Sink](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/connectors/filesystem_sink.html)
#### [Streaming File Sink](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/connectors/streamfile_sink.html)
#### [RabbitMQ](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/connectors/rabbitmq.html)
#### [NiFi](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/connectors/nifi.html)
#### [Twitter](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/connectors/twitter.html)
### [Side Outputs](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/stream/side_output.html)
### [Python API](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/stream/python.html)
### [Testing](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/stream/testing.html)
### [Experimental Features](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/stream/experimental.html)
## 5.4.Batch (DataSet API)

### [Overview](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/batch/)
### [Transformations](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/batch/dataset_transformations.html)
### [Fault Tolerance](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/batch/fault_tolerance.html)
### [Iterations](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/batch/iterations.html)
### [Zipping Elements](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/batch/zip_elements_guide.html)
### [Connectors](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/batch/connectors.html)
### [Python API](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/batch/python.html)
### [Hadoop Compatibility](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/batch/hadoop_compatibility.html)
### [Local Execution](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/local_execution.html)
### [Cluster Execution](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/cluster_execution.html)
## 5.5.Table API & SQL

### [Overview](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/table/)
### [Concepts & Common API](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/table/common.html)
### Streaming Concepts
#### [Overview](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/table/streaming/)
#### [Dynamic Tables](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/table/streaming/dynamic_tables.html)
#### [Time Attributes](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/table/streaming/time_attributes.html)
#### [Joins in Continuous Queries](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/table/streaming/joins.html)
#### [Temporal Tables](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/table/streaming/temporal_tables.html)
#### [Detecting Patterns](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/table/streaming/match_recognize.html)
#### [Query Configuration](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/table/streaming/query_configuration.html)
### [Connect to External Systems](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/table/connect.html)
### [Table API](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/table/tableApi.html)
### [SQL](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/table/sql.html)
### [Built-In Functions](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/table/functions.html)
### [User-defined Sources & Sinks](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/table/sourceSinks.html)
### [User-defined Functions](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/table/udfs.html)
### [SQL Client](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/table/sqlClient.html)
## 5.6.Data Types & Serialization

### [Overview](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/types_serialization.html)
### [Custom Serializers](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/custom_serializers.html)
## 5.7.Managing Execution

### [Execution Configuration](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/execution_configuration.html)
### [Program Packaging](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/packaging.html)
### [Parallel Execution](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/parallel.html)
### [Execution Plans](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/execution_plans.html)
### [Restart Strategies](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/restart_strategies.html)
## 5.8.Libraries

### [Event Processing (CEP)](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/libs/cep.html)
### [Graphs: Gelly](https://ci.apache.org/projects/flink/flink-docs-stable/#collapse-106)
### [Machine Learning](https://ci.apache.org/projects/flink/flink-docs-stable/#collapse-114)
## [5.9.Best Practices](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/best_practices.html)

## [5.10.API Migration Guides](https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/migration.html)

#  6.Deployment & Operations

## 6.1.Clusters & Deployment

### [Standalone Cluster](https://ci.apache.org/projects/flink/flink-docs-release-1.8/ops/deployment/cluster_setup.html)
### [YARN](https://ci.apache.org/projects/flink/flink-docs-release-1.8/ops/deployment/yarn_setup.html)
### [Mesos](https://ci.apache.org/projects/flink/flink-docs-release-1.8/ops/deployment/mesos.html)
### [Docker](https://ci.apache.org/projects/flink/flink-docs-release-1.8/ops/deployment/docker.html)
### [Kubernetes](https://ci.apache.org/projects/flink/flink-docs-release-1.8/ops/deployment/kubernetes.html)
### [AWS](https://ci.apache.org/projects/flink/flink-docs-release-1.8/ops/deployment/aws.html)
### [Google Compute Engine](https://ci.apache.org/projects/flink/flink-docs-release-1.8/ops/deployment/gce_setup.html)
### [MapR](https://ci.apache.org/projects/flink/flink-docs-release-1.8/ops/deployment/mapr_setup.html)
### [Hadoop Integration](https://ci.apache.org/projects/flink/flink-docs-release-1.8/ops/deployment/hadoop.html)
### [Aliyun OSS](https://ci.apache.org/projects/flink/flink-docs-release-1.8/ops/deployment/oss.html)
## [6.2.High Availability (HA)](https://ci.apache.org/projects/flink/flink-docs-release-1.8/ops/jobmanager_high_availability.html)

## 6.3.State & Fault Tolerance

### [Checkpoints](https://ci.apache.org/projects/flink/flink-docs-release-1.8/ops/state/checkpoints.html)
### [Savepoints](https://ci.apache.org/projects/flink/flink-docs-release-1.8/ops/state/savepoints.html)
### [State Backends](https://ci.apache.org/projects/flink/flink-docs-release-1.8/ops/state/state_backends.html)
### [Tuning Checkpoints and Large State](https://ci.apache.org/projects/flink/flink-docs-release-1.8/ops/state/large_state_tuning.html)
## [6.4.Configuration](https://ci.apache.org/projects/flink/flink-docs-release-1.8/ops/config.html)
## [6.5.Production Readiness Checklist](https://ci.apache.org/projects/flink/flink-docs-release-1.8/ops/production_ready.html)
## [6.6.CLI](https://ci.apache.org/projects/flink/flink-docs-release-1.8/ops/cli.html)
## [6.7.Scala REPL](https://ci.apache.org/projects/flink/flink-docs-release-1.8/ops/scala_shell.html)
## [6.8.Kerberos](https://ci.apache.org/projects/flink/flink-docs-release-1.8/ops/security-kerberos.html)
## [6.9.SSL Setup](https://ci.apache.org/projects/flink/flink-docs-release-1.8/ops/security-ssl.html)
## [6.10.File Systems](https://ci.apache.org/projects/flink/flink-docs-release-1.8/ops/filesystems.html)

## [6.11.Upgrading Applications and Flink Versions](https://ci.apache.org/projects/flink/flink-docs-release-1.8/ops/upgrading.html)

#  7.Debugging & Monitoring

## [7.1.Metrics](https://ci.apache.org/projects/flink/flink-docs-release-1.8/monitoring/metrics.html)
## [7.2.Logging](https://ci.apache.org/projects/flink/flink-docs-release-1.8/monitoring/logging.html)
## [7.3.History Server](https://ci.apache.org/projects/flink/flink-docs-release-1.8/monitoring/historyserver.html)
## [7.4.Monitoring Checkpointing](https://ci.apache.org/projects/flink/flink-docs-release-1.8/monitoring/checkpoint_monitoring.html)
## [7.5.Monitoring Back Pressure](https://ci.apache.org/projects/flink/flink-docs-release-1.8/monitoring/back_pressure.html)
## [7.6.Monitoring REST API](https://ci.apache.org/projects/flink/flink-docs-release-1.8/monitoring/rest_api.html)
## [7.7.Debugging Windows & Event Time](https://ci.apache.org/projects/flink/flink-docs-release-1.8/monitoring/debugging_event_time.html)
## [7.8.Debugging Classloading](https://ci.apache.org/projects/flink/flink-docs-release-1.8/monitoring/debugging_classloading.html)
## [7.9.Application Profiling](https://ci.apache.org/projects/flink/flink-docs-release-1.8/monitoring/application_profiling.html)

------

#  8.Flink Development

## [Importing Flink into an IDE](https://ci.apache.org/projects/flink/flink-docs-release-1.8/flinkDev/ide_setup.html)
## [Building Flink from Source](https://ci.apache.org/projects/flink/flink-docs-release-1.8/flinkDev/building.html)

#  9.Internals

## [Component Stack](https://ci.apache.org/projects/flink/flink-docs-release-1.8/internals/components.html)
## [Fault Tolerance for Data Streaming](https://ci.apache.org/projects/flink/flink-docs-release-1.8/internals/stream_checkpointing.html)
## [Jobs and Scheduling](https://ci.apache.org/projects/flink/flink-docs-release-1.8/internals/job_scheduling.html)

## [Task Lifecycle](https://ci.apache.org/projects/flink/flink-docs-release-1.8/internals/task_lifecycle.html)
## [File Systems](https://ci.apache.org/projects/flink/flink-docs-release-1.8/internals/filesystems.html)

# [Javadocs](https://ci.apache.org/projects/flink/flink-docs-release-1.8/api/java)

# [Scaladocs](https://ci.apache.org/projects/flink/flink-docs-release-1.8/api/scala/index.html#org.apache.flink.api.scala.package)

# [Project Page](http://flink.apache.org/)