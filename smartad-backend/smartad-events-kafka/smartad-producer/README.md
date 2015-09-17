
```
sbt run
[info] Running event.pipeline.producer.EventProducerApp 

log4j:WARN No appenders could be found for logger (kafka.utils.VerifiableProperties).
log4j:WARN Please initialize the log4j system properly.
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.

Starting events stream production . . .  .    ................

```

zk-log

```
[2015-09-17 16:43:10,328] INFO Got user-level KeeperException when processing sessionid:0x14fdd387ece0000 type:create cxid:0x51 zxid:0xfffffffffffffffe txntype:unknown reqpath:n/a Error Path:/brokers/topics/smartad_events/partitions/0 Error:KeeperErrorCode = NoNode for /brokers/topics/smartad_events/partitions/0 (org.apache.zookeeper.server.PrepRequestProcessor)
[2015-09-17 16:43:10,398] INFO Got user-level KeeperException when processing sessionid:0x14fdd387ece0000 type:create cxid:0x52 zxid:0xfffffffffffffffe txntype:unknown reqpath:n/a Error Path:/brokers/topics/smartad_events/partitions Error:KeeperErrorCode = NoNode for /brokers/topics/smartad_events/partitions (org.apache.zookeeper.server.PrepRequestProcessor)
[2015-09-17 16:43:10,522] INFO Got user-level KeeperException when processing sessionid:0x14fdd387ece0000 type:create cxid:0x58 zxid:0xfffffffffffffffe txntype:unknown reqpath:n/a Error Path:/brokers/topics/smartad_events/partitions/1 Error:KeeperErrorCode = NoNode for /brokers/topics/smartad_events/partitions/1 (org.apache.zookeeper.server.PrepRequestProcessor)

```

kafka-log

```
[2015-09-17 16:43:10,046] INFO [KafkaApi-0] Auto creation of topic smartad_events with 2 partitions and replication factor 1 is successful! (kafka.server.KafkaApis)
[2015-09-17 16:43:10,458] INFO Closing socket connection to /127.0.0.1. (kafka.network.Processor)
[2015-09-17 16:43:10,499] INFO Closing socket connection to /127.0.0.1. (kafka.network.Processor)
[2015-09-17 16:43:10,591] INFO [ReplicaFetcherManager on broker 0] Removed fetcher for partitions [smartad_events,0],[smartad_events,1] (kafka.server.ReplicaFetcherManager)
[2015-09-17 16:43:10,622] INFO Closing socket connection to /127.0.0.1. (kafka.network.Processor)
[2015-09-17 16:43:10,637] INFO Completed load of log smartad_events-0 with log end offset 0 (kafka.log.Log)
[2015-09-17 16:43:10,639] INFO Closing socket connection to /127.0.0.1. (kafka.network.Processor)
[2015-09-17 16:43:10,658] INFO Created log for partition [smartad_events,0] in /usr/local/kafka/~/bin/kafka-logs with properties {segment.index.bytes -> 10485760, file.delete.delay.ms -> 60000, segment.bytes -> 536870912, flush.ms -> 9223372036854775807, delete.retention.ms -> 86400000, index.interval.bytes -> 4096, retention.bytes -> -1, cleanup.policy -> delete, segment.ms -> 604800000, max.message.bytes -> 1000012, flush.messages -> 9223372036854775807, min.cleanable.dirty.ratio -> 0.5, retention.ms -> 604800000}. (kafka.log.LogManager)
[2015-09-17 16:43:10,677] WARN Partition [smartad_events,0] on broker 0: No checkpointed highwatermark is found for partition [smartad_events,0] (kafka.cluster.Partition)

```