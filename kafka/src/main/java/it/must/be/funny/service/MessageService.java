package it.must.be.funny.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class MessageService {
    public void processMessage(ConsumerRecord<String, String> record, ChainRule chainRule){
        //logic process message here
        //1. data checking todo


        //2.redis checking todo


    }
}
