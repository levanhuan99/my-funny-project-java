package it.must.be.funny.model;

public class ConfigProperties {
    private KafkaConfigProperties kafkaConfigProperties;
    private long cacheCallExpiredTimeMillisec;

    public KafkaConfigProperties getKafkaConfigProperties() {
        return kafkaConfigProperties;
    }

    public void setKafkaConfigProperties(KafkaConfigProperties kafkaConfigProperties) {
        this.kafkaConfigProperties = kafkaConfigProperties;
    }

    public long getCacheCallExpiredTimeMillisec() {
        return cacheCallExpiredTimeMillisec;
    }

    public void setCacheCallExpiredTimeMillisec(long cacheCallExpiredTimeMillisec) {
        this.cacheCallExpiredTimeMillisec = cacheCallExpiredTimeMillisec;
    }
}
