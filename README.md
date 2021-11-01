# Spring Boot Kafka Consumer

    This application demonstrates kafka consumer with spring boot.

### Kafka Commands

Download Kafka binaries from [Apache Kafka](https://kafka.apache.org/downloads)

1. Start Zookeeper
```bash
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
```

2. Start Kafka Server
```bash
.\bin\windows\kafka-server-start.bat .\config\server.properties
```

3. Create Kafka Topic
```bash
.\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic TestTopic
```

4. Place Message in Kafka Topic
```bash
> .\bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic TestTopic
> {"title":"UyarValluvam","isbn":"SL2222KAMBABAHR"}
```

5. Verify Message in Kafka Topic
```bash
.\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic TestTopic
```

### Build and Run
```bash
mvn clean install
mvn spring-boot:run
```

### Consuming with Group ID for Partition Distribution
1. We can consume message from multiple topics with explicit names and group id.
2. We can consume message from multiple topics with pattern names and group id.
3. We can consume message from multiple topics with TopicPartition details and group id.
