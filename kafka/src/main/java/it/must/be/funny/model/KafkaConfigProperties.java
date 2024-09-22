package it.must.be.funny.model;

public class KafkaConfigProperties {
    private String groupId;
    private String topicName;
    private String bootstrapServer;
    private int threadPoolSize;
    private String topicTimeOutData;

    public KafkaConfigProperties() {
    }

    public KafkaConfigProperties(String groupId, String topicName, String bootstrapServer, int threadPoolSize) {
        this.groupId = groupId;
        this.topicName = topicName;
        this.bootstrapServer = bootstrapServer;
        this.threadPoolSize = threadPoolSize;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getBootstrapServer() {
        return bootstrapServer;
    }

    public void setBootstrapServer(String bootstrapServer) {
        this.bootstrapServer = bootstrapServer;
    }

    public int getThreadPoolSize() {
        return threadPoolSize;
    }

    public void setThreadPoolSize(int threadPoolSize) {
        this.threadPoolSize = threadPoolSize;
    }

    public String getTopicTimeOutData() {
        return topicTimeOutData;
    }

    public void setTopicTimeOutData(String topicTimeOutData) {
        this.topicTimeOutData = topicTimeOutData;
    }

    @Override
    public String toString() {
        return "KafkaConfigProperties{" +
                "groupId='" + groupId + '\'' +
                ", topicName='" + topicName + '\'' +
                ", bootstrapServer='" + bootstrapServer + '\'' +
                ", threadPoolSize=" + threadPoolSize +
                ", topicTimeOutData='" + topicTimeOutData + '\'' +
                '}';
    }
}
