clean_all:
	@docker-compose down --rmi all

start_kafka:
	@docker-compose up -d

docker_build:
	@docker build -t demo_kafka_producer:0.0.1 .

run_app: docker_build
	@docker run demo_kafka_producer:0.0.1