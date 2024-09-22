package it.must.be.funny;

import it.must.be.funny.config.SnakeYmlConfigLoader;
import it.must.be.funny.consumer.KafkaMessageProcessor;
import it.must.be.funny.consumer.KafkaMultiTopicConsumer;
import it.must.be.funny.consumer.KafkaMultiTopicConsumerInterface;
import it.must.be.funny.consumer.KafkaProduce;
import it.must.be.funny.model.ConfigProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KafkaMultiTopicConsumeMain {
    private static final Logger logger = LoggerFactory.getLogger(KafkaMultiTopicConsumeMain.class);

    public static void main(String[] args) {
        ConfigProperties configProperties = SnakeYmlConfigLoader.loadConfig();
        final ExecutorService threadProcessData = Executors.newSingleThreadExecutor();
        KafkaProduce kafkaProduce = new KafkaProduce(configProperties.getKafkaConfigProperties().getBootstrapServer());
        KafkaMultiTopicConsumerInterface<String, String> processor = (key, value) -> {
            logger.info(String.format("end of data with key %s and value %s", key, value.toString()));
            kafkaProduce.produce("topic_merge_data", key, value.toString());
            //produce data
        };

        KafkaMultiTopicConsumer kafkaMultiTopicConsumer = new KafkaMultiTopicConsumer<>(processor
                , configProperties.getKafkaConfigProperties().getBootstrapServer(),
                configProperties.getKafkaConfigProperties().getTopicName(), 100);
        threadProcessData.submit(
                () -> {
                   kafkaMultiTopicConsumer.start("topic_1");
                }
        );
        logger.info("Start Success");
    }
}
