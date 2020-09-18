package com.example.kafka.client

import java.util.Properties

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.Serdes

object Config {

  private val stringSerializer: String = "org.apache.kafka.common.serialization.StringSerializer"
  private val stringDeSerializer: String = "org.apache.kafka.common.serialization.StringDeserializer"
  private val servers = "localhost:9092"

  def getConsumerConfig: Properties = {
    val props = new Properties
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, servers)
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, stringDeSerializer)
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, stringDeSerializer)
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "consumer-1")
    props
  }

  def getProducerConfig: Properties = {
    val props = new Properties
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, servers)
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, stringSerializer)
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, stringSerializer)
    props
  }
}
