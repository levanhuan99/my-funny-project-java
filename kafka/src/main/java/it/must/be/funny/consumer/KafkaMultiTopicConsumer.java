package it.must.be.funny.consumer;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalCause;
import com.github.benmanes.caffeine.cache.RemovalListener;
import it.must.be.funny.KafkaMultiTopicConsumeMain;
import it.must.be.funny.config.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class KafkaMultiTopicConsumer<K, V> {
    private static final Logger logger = LoggerFactory.getLogger(KafkaMultiTopicConsumer.class);
    private final KafkaConsumer<K, V> consumer;

    // Sử dụng Caffeine Cache để cache dữ liệu các bản tin có cung key
    // set timeout nếu thời điểm tác động cuối cùng vào key vượt thời gian timeout
    private final Cache<K, List<V>> messageCache;
    private final ExecutorService threadProcessData;
    private final KafkaMultiTopicConsumerInterface<K, V> messageProcessor;
    public KafkaMultiTopicConsumer(KafkaMultiTopicConsumerInterface<K, V> messageProcessor, String bootstrapServer, String groupId, int dataTimeout) {
        this.consumer = new KafkaConsumer<>(ConsumerConfig.getProperties(bootstrapServer, groupId));
        this.messageCache =  Caffeine.newBuilder()
                .expireAfterWrite(dataTimeout, TimeUnit.SECONDS) // Tự động loại bỏ sau timeout giây
                .removalListener(new RemovalListener<K, List<V>>() {
                    @Override
                    public void onRemoval(K key, List<V> messages, RemovalCause cause) {
                        if (cause == RemovalCause.EXPIRED) {
                            // Xử lý bản tin khi key hết hạn
                            //counter metrics here
                            logger.info("data timeout");
                        }
                    }
                })
                .build();
        this.threadProcessData = Executors.newSingleThreadExecutor();
        this.messageProcessor = messageProcessor;
    }

    public void start(String... topics){
        consumer.subscribe(Arrays.asList(topics));
        try {
            while (true) {
                // Poll dữ liệu từ Kafka
                ConsumerRecords<K, V> records = consumer.poll(Duration.ofMillis(1000));
                // Xử lý dữ liệu từ cả hai topic
                //sử dụng if else để process dữ liệu khác topic nhau
                for (ConsumerRecord<K, V> record : records) {
                    if (record.key() != null) {
                        logger.info(String.format("Topic: %s, Partition: %d, Offset: %d, Key: %s, Value: %s%n",
                                record.topic(), record.partition(), record.offset(), record.key(), record.value()));
                        messageCache.asMap().computeIfAbsent(record.key(), k -> new ArrayList<>()).add(record.value());

                        if (isEndOfCall(record.value())) {
                            processMessages(record.key());
                        }
                    }
                }
            }
        } finally {
            consumer.close();
        }
    }

    //sự kiện khi có key bị expired
    //counter metrics here
    private void processMessages(K key){
        threadProcessData.submit(() -> {
            try {
                // Thực hiện logic xử lý tại đây
                if (key != null){
                    List<V> value = messageCache.getIfPresent(key);
                    messageProcessor.processData(key, value);
                }
                else {
                    //increase counter todo
                }
            } catch (Exception e) {
                e.printStackTrace();
                // Xử lý lỗi nếu có
            }
        });

    }

    private boolean isEndOfCall(V value){
        return value.toString().contains("DONE");

    }
}
