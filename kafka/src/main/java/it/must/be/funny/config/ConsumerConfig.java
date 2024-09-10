package it.must.be.funny.config;

import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Properties;

public class ConsumerConfig {
    public static Properties getProperties(String bootstrapServers, String groupId){
        Properties properties = new Properties();
        properties.put(org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers); // Replace with your Kafka brokers
        properties.put(org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG, groupId); // Replace with your consumer group
        properties.put(org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(org.apache.kafka.clients.consumer.ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.put(org.apache.kafka.clients.consumer.ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false"); // Disable auto commit for better performance
        return properties;
    }
}
