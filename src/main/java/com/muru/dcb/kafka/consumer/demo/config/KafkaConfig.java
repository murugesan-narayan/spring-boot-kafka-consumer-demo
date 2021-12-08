package com.muru.dcb.kafka.consumer.demo.config;

import com.muru.dcb.kafka.consumer.demo.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
@Slf4j
public class KafkaConfig {
    @Autowired
    private ConsumerFactory<String, Book> consumerFactory;

    public DefaultKafkaConsumerFactory<String, Book> defaultKafkaConsumerFactory() {
        Map<String, Object> config = new HashMap<>(consumerFactory.getConfigurationProperties());
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        config.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class);
        //config.put(JsonDeserializer.KEY_DEFAULT_TYPE, "com.example.MyKey")
        config.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());
//        config.put(JsonDeserializer.TRUSTED_PACKAGES, "com.muru.dcb.kafka.consumer");
        config.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.muru.dcb.kafka.consumer.demo.model.Book");
//        JsonDeserializer<Book> bookJsonDeserializer = new JsonDeserializer<>(Book.class);
        return new DefaultKafkaConsumerFactory<>(config);
//        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),bookJsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Book> concurrentKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Book> concurrentKafkaListenerContainerFactory =
                new ConcurrentKafkaListenerContainerFactory<>();
        concurrentKafkaListenerContainerFactory.setConsumerFactory(defaultKafkaConsumerFactory());
        return concurrentKafkaListenerContainerFactory;
    }

}
