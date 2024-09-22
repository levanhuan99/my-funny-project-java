package it.must.be.funny.test;

import it.must.be.funny.produce.KafkaProduce;
import it.must.be.funny.test.model.ACR;
import it.must.be.funny.test.model.ERB;
import it.must.be.funny.test.model.IDP;
import it.must.be.funny.test.model.RC;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.Callable;

public class CallSimulation implements Callable<Void> {
    private final String caller;
    private final String callee;
    private final KafkaProducer<String, String> producer;
    private final static String TOPIC = "topic_1";
    private final static Properties props = new Properties();

    static {
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"); // Kafka broker
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    }

    public CallSimulation(String caller, String callee) {
        this.caller = caller;
        this.callee = callee;
        producer = new KafkaProducer<>(props);
    }

    @Override
    public Void call() throws Exception {
        // Bắt đầu cuộc gọi
        IDP idp = new IDP(caller, callee);
        producer.send(new ProducerRecord<>(TOPIC, idp.getCallId(), idp.formatIDP()));

        // Mô phỏng kết nối thành công
        Thread.sleep((int) (Math.random() * 10)); // Giả lập độ trễ kết nối ngẫu nhiên
        ERB erbConnected = new ERB(idp.getCallId(), "Connected");
        producer.send(new ProducerRecord<>(TOPIC, erbConnected.getCallId(), erbConnected.formatERB()));

        // Mô phỏng cuộc gọi diễn ra
        Thread.sleep((int) (Math.random() * 10000)); // Giả lập thời gian cuộc gọi ngẫu nhiên
        ACR acr = new ACR(idp.getCallId(), Math.random() * 5); // Giả lập cước phí ngẫu nhiên
        producer.send(new ProducerRecord<>(TOPIC, acr.getCallId(), acr.formatACR()));

        // Mô phỏng ngắt kết nối
        Thread.sleep((int) (Math.random() * 5)); // Giả lập độ trễ ngắt kết nối ngẫu nhiên
        RC rc = new RC(idp.getCallId(), "Normal release");
        producer.send(new ProducerRecord<>(TOPIC, rc.getCallId(), rc.formatRC()));
        // Mô phỏng báo cáo kết thúc cuộc gọi
        ERB erbReleased = new ERB(idp.getCallId(), "Released");
        producer.send(new ProducerRecord<>(TOPIC, erbReleased.getCallId(), erbReleased.formatERB()));
        return null;
    }
}
