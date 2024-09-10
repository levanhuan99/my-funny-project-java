package it.must.be.funny;


import it.must.be.funny.consumer.KafkaConsumerGroup;
import it.must.be.funny.consumer.KafkaMessageProcessor;
import it.must.be.funny.service.ChainRule;
import it.must.be.funny.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class KafkaMain {
    private static final Logger logger = LoggerFactory.getLogger(KafkaMain.class);

    public static void main(String[] args) {
        MessageService messageService = new MessageService();
        ChainRule chainRule = ChainRule.getInstance();

        // Add your message processing logic here
        KafkaMessageProcessor<String, String> processor = record -> {
            messageService.processMessage(record, chainRule);

        };


        // Create and start the KafkaConsumerClient
        String boostrapServer = "localhost:9092";
        String groupId = "group_test";
        KafkaConsumerGroup<String, String> kafkaConsumerClient = new KafkaConsumerGroup<>(processor, 10,boostrapServer,groupId);
        kafkaConsumerClient.start("first_topic");

        }
}