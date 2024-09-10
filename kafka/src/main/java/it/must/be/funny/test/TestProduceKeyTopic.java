package it.must.be.funny.test;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.Random;

public class TestProduceKeyTopic {
    public static void main(String[] args) {
        // Thiết lập cấu hình cho Producer
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"); // Kafka broker
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // Tạo Kafka Producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        String topic = "first_topic";

        Random random = new Random();
        // Gửi message với cùng key
        int totalNumber = 20000000;
        int i = 0;
        while (true){
            String key = String.valueOf(random.nextInt(9999) + 1);
            String value = "Message content" + i;
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
            producer.send(record);
            i++;
        }

        // Đóng Producer
//        producer.close();
    }
}
