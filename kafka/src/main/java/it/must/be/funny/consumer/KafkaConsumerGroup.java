package it.must.be.funny.consumer;

import it.must.be.funny.config.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KafkaConsumerGroup<K, V> {
    private final KafkaConsumer<K, V> consumer;
    private final KafkaMessageProcessor<K, V> messageProcessor;
    private final ExecutorService executorService;

    public KafkaConsumerGroup(KafkaMessageProcessor<K, V> messageProcessor, int threadPoolSize,String bootstrapServer, String groupId) {
        this.consumer = new KafkaConsumer<>(ConsumerConfig.getProperties(bootstrapServer,groupId));
        this.messageProcessor = messageProcessor;
        this.executorService = Executors.newFixedThreadPool(threadPoolSize);
    }


    /**
     *
     * use: create your own class
     *         // Implement the KafkaMessageProcessor
     *         KafkaMessageProcessor<String, String> processor = record -> {
     *             System.out.printf("Processing record with key %s and value %s%n", record.key(), record.value());
     *             // Add your message processing logic here
     *         };
     *
     *         // Create and start the KafkaConsumerClient
     *         KafkaConsumerClient<String, String> kafkaConsumerClient = new KafkaConsumerClient<>(properties, processor, 10);
     *         kafkaConsumerClient.start("your-topic");
     */
    public void start(String topic) {
        consumer.subscribe(Collections.singletonList(topic));

        try {
            while (true) {
                var records = consumer.poll(100);
//                executorServiceMonitor.submit(messageProcessor :: monitor);
                for (ConsumerRecord<K, V> record : records) {
                    executorService.submit(
                            () -> messageProcessor.process(record));
                }
                consumer.commitSync();
            }
        } catch (Exception e) {
            e.printStackTrace();
            //todo logger err
        } finally {
            consumer.close();
            executorService.shutdown();
        }
    }

}
