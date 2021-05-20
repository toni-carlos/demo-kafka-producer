FROM gradle:jdk11 as builder

LABEL maintainer="tonicadc@gmail.com"

COPY --chown=gradle:gradle . /home/gradle/src/
WORKDIR /home/gradle/src/
RUN gradle clean build

FROM openjdk:11-jdk-slim

COPY --from=builder /home/gradle/src/build/libs/demo-kafka-producer-*.jar /opt/demo-kafka-producer.jar

WORKDIR /opt

RUN sh -c 'touch demo-kafka-producer'

ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar demo-kafka-producer.jar