package com.demo.producer.config;

public class ApplicationProperties {

  private String bootstrapServers;
  private String schemaRegistryUrl;
  private String outpuTopic;
  private String jsonDataPath;

  public String getBootstrapServers() {
    return bootstrapServers;
  }

  public void setBootstrapServers(String bootstrapServers) {
    this.bootstrapServers = bootstrapServers;
  }

  public String getSchemaRegistryUrl() {
    return schemaRegistryUrl;
  }

  public void setSchemaRegistryUrl(String schemaRegistryUrl) {
    this.schemaRegistryUrl = schemaRegistryUrl;
  }

  public String getOutpuTopic() {
    return outpuTopic;
  }

  public void setOutpuTopic(String outpuTopic) {
    this.outpuTopic = outpuTopic;
  }

  public String getJsonDataPath() {
    return jsonDataPath;
  }

  public void setJsonDataPath(String jsonDataPath) {
    this.jsonDataPath = jsonDataPath;
  }
}
