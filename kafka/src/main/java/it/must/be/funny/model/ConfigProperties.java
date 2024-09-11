package it.must.be.funny.model;

public class ConfigProperties {
    private KafkaConfigProperties kafkaConfigProperties;

    public KafkaConfigProperties getKafkaConfigProperties() {
        return kafkaConfigProperties;
    }

    public void setKafkaConfigProperties(KafkaConfigProperties kafkaConfigProperties) {
        this.kafkaConfigProperties = kafkaConfigProperties;
    }
}
