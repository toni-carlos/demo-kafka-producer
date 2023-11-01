#!/usr/bin/env bash

# create schema user-events-valeu
curl -X POST -H "Content-Type: application/vnd.schemaregistry.v1+json" \
    -d '{ "schema": "{\"name\": \"UserEventsValue\", \"type\": \"record\", \"namespace\": \"com.demo.avro\", \"fields\": [{\"name\": \"anonymous_id\", \"type\": \"int\"}, {\"name\": \"action\", \"type\": [\"null\", \"string\"], \"default\": null}, {\"name\": \"event_timestamp\", \"type\": [\"null\", \"string\"], \"default\": null}, {\"name\": \"event_datetime\", \"type\": [\"null\", \"string\"], \"default\": null}]}"}' \
http://localhost:8081/subjects/user-events-value/versions

# create schema user-events-key
curl -X POST -H "Content-Type: application/vnd.schemaregistry.v1+json" \
    -d '{ "schema": "{\"name\": \"UserEventsKey\", \"type\": \"record\", \"namespace\": \"com.demo.avro\", \"fields\": [{\"name\": \"anonymous_id\", \"type\": \"int\"}]}"}' \
http://localhost:8081/subjects/user-events-key/versions

echo "Schema Criados:"
curl -i -X GET http://localhost:8081/subjects