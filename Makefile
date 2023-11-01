create_schemas:
	@./scripts/create-schemas.sh

list_schemas:
	@curl -i -X GET http://localhost:8081/subjects

clean_services:
	@docker-compose down --rmi all

clean_app:
	@docker rm java_app
	@docker rmi java_app

build_app:
	@docker build -t java_app .
	@docker run -it --name java_app java_app

build_services:
	@docker-compose up -d

create_topic:
	@docker exec kafka /bin/kafka-topics --create \
	--topic user-events \
	--replication-factor 1 \
	--partitions 1 \
	--bootstrap-server localhost:9092

list_topics:
	@docker exec kafka /bin/kafka-topics --list --bootstrap-server localhost:9092

describe_schema:
	@curl -X GET http://localhost:8081/subjects/${SCHEMA}/versions/1

consumer_topic:
	@docker exec schema-registry /bin/kafka-avro-console-consumer \
	--bootstrap-server kafka:29092 \
	--property schema.registry.url=http://schema-registry:8081 \
	--topic ${TOPIC} \
	--property print.key=true \
	--property print.value=true \
	--value-deserializer io.confluent.kafka.serializers.KafkaAvroDeserializer \
	--key-deserializer org.apache.kafka.common.serialization.StringDeserializer \
	--from-beginning


delete_topic:
	@docker exec kafka /bin/kafka-topics --bootstrap-server localhost:9092