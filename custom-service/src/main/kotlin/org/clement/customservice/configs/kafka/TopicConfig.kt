package org.clement.customservice.configs.kafka

import org.apache.kafka.clients.admin.NewTopic
import org.clement.customservice.model.Customer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.KafkaListener

@Configuration
class TopicConfig {

    @Bean
    fun topic() = NewTopic("customerCreated",10,1)

    @KafkaListener(topics = ["customerCreated"], groupId = "groundId")
    fun lister(data : Customer?) = println("Listener Received : ${data?.firstName}")
}