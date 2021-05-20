# Demo Kafka Producer

This application is an example of using [Kafka Client](https://kafka.apache.org/26/javadoc/index.htmfol?org/apache/kafka/clients/producer/KafkaProducer.html) to produce messages in the cluster Kafka.
As a test, the [Mockaroo](https://www.mockaroo.com/) database was downloaded in json format and saved in the src / resources / data directory.
It was also necessary to create the avro schemas (src / resources / avro) to publish the data in the topic in avro format.

## Build Requirements

- JDK 11
- Gradlew
- Docker + docker-compose

## Installation

- Clone this repo to your local machine using `https://github.com/toni-carlos/demo-kafka-producer.git`

### Start a Kafka cluster with Schema-Registry 

```shell
$ docker-compose up -d
$ docker-compose ps
```

### Setup

- Make sure you've made changes to `config/application.yml`
- In the Schema Registry, register a new version of a schema for the [value](https://github.com/toni-carlos/demo-kafka-producer/blob/main/src/main/resources/avro/mockaroo_value.avsc) 
  under the subject "mockaroo-events-value" and another for the [key](https://github.com/toni-carlos/demo-kafka-producer/blob/main/src/main/resources/avro/mockaroo_key.avsc) under the subject "mockaroo-events-key".
  You can use the [Shell](https://pypi.org/project/python-schema-registry-client/) or the [Python](https://pypi.org/project/python-schema-registry-client/)

Example using [Python](https://pypi.org/project/python-schema-registry-client/)

```
from schema_registry.client import SchemaRegistryClient, schema

client = SchemaRegistryClient(url="http://127.0.0.1:8081")

deployment_schema_key = {
  "name": "MockarooKey",
  "type": "record",
  "namespace": "com.demo.producer.avro",
  "fields": [
    {
      "name": "id",
      "type": "int"
    }
  ]
}

deployment_schema_value = {
"name": "MockarooValue",
"type": "record",
"namespace": "com.demo.producer.avro",
"fields": [
  {
    "name": "id",
    "type": "int"
  },
  {
    "name": "first_name",
    "type": ["null", "string"],
    "default": None
  },
  {
    "name": "last_name",
    "type": ["null", "string"],
    "default": None
  },
  {
    "name": "email",
    "type": ["null", "string"],
    "default": None
  },
  {
    "name": "gender",
    "type": ["null", "string"],
    "default": None
  },
  {
    "name": "ip_address",
    "type": ["null", "string"],
    "default": None
  }
]
}

avro_schema_key = schema.AvroSchema(deployment_schema_key)
client.register("mockaroo-events-key", avro_schema_key)

avro_schema_value = schema.AvroSchema(deployment_schema_value)
client.register("mockaroo-events-value", avro_schema_value)

```

List all the subjects:

```shell
$ curl -X GET http://localhost:8081/subjects
```

## How to Run

```shell
$ ./gradlew clean run
```

## Consume message from 'mockaroo-events' topic using [Kafkacat](https://docs.confluent.io/3.3.0/app-development/kafkacat-usage.html)

### Installation

```shell
$ sudo apt update
$ sudo apt install kafkacat
```

### Command line
```shell
$ kafkacat -C -b localhost:9092 -t mockaroo-events -c 5
```
