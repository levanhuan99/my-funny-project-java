package it.must.be.funny.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

public interface KafkaMessageProcessor<K, V> {
    void process(ConsumerRecord<K, V> record);

}
