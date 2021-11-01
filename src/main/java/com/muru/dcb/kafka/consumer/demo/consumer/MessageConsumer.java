package com.muru.dcb.kafka.consumer.demo.consumer;

import com.muru.dcb.kafka.consumer.demo.model.Book;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @KafkaListener( topics = "TestTopic", groupId = "Group-1",
            containerFactory = "concurrentKafkaListenerContainerFactory")
    public void consume(Book book) {
        System.out.println("Received Message = " + book);
    }
}
