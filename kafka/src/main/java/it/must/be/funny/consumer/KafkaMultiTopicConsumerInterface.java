package it.must.be.funny.consumer;

import java.util.List;

public interface KafkaMultiTopicConsumerInterface<K, V> {
    public void processData(K key, List<V> values);
}
