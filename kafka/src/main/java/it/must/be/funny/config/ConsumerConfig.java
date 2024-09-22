package it.must.be.funny.config;

import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Properties;

public class ConsumerConfig {
    public static Properties getProperties(String bootstrapServers, String groupId){
        Properties properties = new Properties();
        //todo change to consume data from latest offset when app has startedmmit for better performa
        properties.put(org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.put(org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(org.apache.kafka.clients.consumer.ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false"); // Disable auto commit
        properties.put(org.apache.kafka.clients.consumer.ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest"); // auto fetch lastest offset data
        return properties;
    }
}
