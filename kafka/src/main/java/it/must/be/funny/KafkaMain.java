package it.must.be.funny;


import it.must.be.funny.consumer.KafkaConsumerGroup;
import it.must.be.funny.consumer.KafkaMessageProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class KafkaMain {
    private static final Logger logger = LoggerFactory.getLogger(KafkaMain.class);

    public static void main(String[] args) {
        // Implement the KafkaMessageProcessor
        KafkaMessageProcessor<String, String> processor = record -> {
            System.out.printf("Processing record with key %s and value %s%n", record.key(), record.value());
            // Add your message processing logic here
        };

        // Create and start the KafkaConsumerClient
        String boostrapServer = "localhost:9092";
        String groupId = "group_test";
        KafkaConsumerGroup<String, String> kafkaConsumerClient = new KafkaConsumerGroup<>(processor, 10,boostrapServer,groupId);
        kafkaConsumerClient.start("first_topic");

    }
}