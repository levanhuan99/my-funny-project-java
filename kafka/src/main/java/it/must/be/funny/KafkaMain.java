package it.must.be.funny;


import it.must.be.funny.config.SnakeYmlConfigLoader;
import it.must.be.funny.consumer.KafkaConsumerGroup;
import it.must.be.funny.consumer.KafkaMessageProcessor;
import it.must.be.funny.model.ConfigProperties;
import it.must.be.funny.model.KafkaConfigProperties;
import it.must.be.funny.service.ChainRule;
import it.must.be.funny.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class KafkaMain {
    private static final Logger logger = LoggerFactory.getLogger(KafkaMain.class);

    public static void main(String[] args) {
        ConfigProperties configProperties = SnakeYmlConfigLoader.loadConfig();
        MessageService messageService = new MessageService();
        ChainRule chainRule = ChainRule.getInstance();

        // Add your message processing logic here
        KafkaMessageProcessor<String, String> processor = record -> {
            messageService.processMessage(record, chainRule);

        };


        // Create and start the KafkaConsumerClient
        KafkaConsumerGroup<String, String> kafkaConsumerClient = new KafkaConsumerGroup<>(processor,
                configProperties.getKafkaConfigProperties().getThreadPoolSize(),
                configProperties.getKafkaConfigProperties().getBootstrapServer(),
                configProperties.getKafkaConfigProperties().getGroupId());

        kafkaConsumerClient.start(configProperties.getKafkaConfigProperties().getTopicName());

        }
}