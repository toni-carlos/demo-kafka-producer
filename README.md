# Demo Kafka Producer

This application is an example of using [Kafka Client](https://kafka.apache.org/26/javadoc/index.htmfol?org/apache/kafka/clients/producer/KafkaProducer.html) to produce messages in the cluster Kafka.
The data sent to the topic is in [MOCK_DATA.json](https://github.com/toni-carlos/demo-kafka-producer/blob/producer-user-event/src/main/resources/data/MOCK_DATA.json).

## Requirements

- JDK 11
- Gradle 7.4 +
- Docker + docker-compose

## Clone Repository

- Clone this repo to your local machine using `https://github.com/toni-carlos/demo-kafka-producer.git`
- git checkout producer-user-event

## Running the project

### 1. Starting Kafka

```shell
make build_services
```

### 2. Create the input topics.

```shell
make create_topic
```

### 3. Create avro schema in Schema Registry

```shell
chmod +x scripts
make create_schemas
```

### 4. Now let's run the application
```shell
gradle build run
```

### 5. Validate that data was sent to the topic.

```shell
chmod +x scripts
make consumer_topic TOPIC=user-events
```

## Clean services created to run the demo
```shell
make clean_services
```