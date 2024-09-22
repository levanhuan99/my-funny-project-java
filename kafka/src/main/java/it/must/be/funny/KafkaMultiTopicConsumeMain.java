package it.must.be.funny;

import it.must.be.funny.config.SnakeYmlConfigLoader;
import it.must.be.funny.consumer.KafkaMultiTopicConsumer;
import it.must.be.funny.consumer.KafkaMultiTopicConsumerInterface;
import it.must.be.funny.produce.KafkaProduce;
import it.must.be.funny.model.ConfigProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KafkaMultiTopicConsumeMain {
    private static final Logger logger = LoggerFactory.getLogger(KafkaMultiTopicConsumeMain.class);

    public static void main(String[] args) {
        ConfigProperties configProperties = SnakeYmlConfigLoader.loadConfig();
        final ExecutorService threadProcessData = Executors.newFixedThreadPool(2);
        KafkaProduce kafkaProduce = new KafkaProduce(configProperties.getKafkaConfigProperties().getBootstrapServer());
        KafkaMultiTopicConsumerInterface<String, String> processor = (key, value) -> {
            logger.info(String.format("end of data with key %s and value %s", key, value.toString()));
            kafkaProduce.produce("topic_merge_data", key, value.toString());
        };

        KafkaMultiTopicConsumer kafkaMultiTopicConsumer = new KafkaMultiTopicConsumer<>(processor
                ,configProperties, kafkaProduce);
        threadProcessData.submit(
                () -> {
                   kafkaMultiTopicConsumer.start("topic_1", "topic_2");
                }
        );
        logger.info("Start Success");
    }
}
