package org.clement.customservice.configs.kafka

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.clement.customservice.model.Customer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.kafka.support.serializer.JsonSerializer

@Configuration
class ConsumerConfig {

   val consumerProps = mapOf(
       ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to "localhost:9093",
       ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
       ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to JsonSerializer::class.java
   )

    @Bean
   fun consumerFactory() : ConsumerFactory<String,Customer>{
       val messageDeserializer = JsonDeserializer(Customer::class.java)
       messageDeserializer.addTrustedPackages("*")
       return DefaultKafkaConsumerFactory(consumerProps, StringDeserializer(),messageDeserializer)
   }

    @Bean
    fun kafkaListenerContainerFactory(consumerFactory: ConsumerFactory<String,Customer>) =
        ConcurrentKafkaListenerContainerFactory<String,Customer>().also { it.consumerFactory = consumerFactory }
}