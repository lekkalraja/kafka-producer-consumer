package com.example.kafka.client

import java.util.Properties

import org.apache.kafka.clients.producer.ProducerConfig

object Config {

  def getConsumerConfig: Properties = {
    val props = new Properties
    props.put("bootstrap.servers", "tricycle-01.srvs.cloudkafka.com:9094,tricycle-02.srvs.cloudkafka.com:9094,tricycle-03.srvs.cloudkafka.com:9094")
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("group.id", "s78m90xp-consumer-1")
    props.put("enable.auto.commit", "true")
    props.put("auto.commit.interval.ms", "1000")
    props.put("auto.offset.reset", "earliest")
    props.put("session.timeout.ms", "30000")
    props.put("security.protocol", "SASL_SSL")
    props.put("sasl.mechanism", "SCRAM-SHA-256")
    props.put("sasl.jaas.config", getJaasCFG)
    props
  }

  def getProducerConfig: Properties = {
    val props = new Properties
    props.put("bootstrap.servers", "tricycle-01.srvs.cloudkafka.com:9094,tricycle-02.srvs.cloudkafka.com:9094,tricycle-03.srvs.cloudkafka.com:9094")
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
    props.put("security.protocol", "SASL_SSL")
    props.put("sasl.mechanism", "SCRAM-SHA-256")
    props.put("sasl.jaas.config", getJaasCFG)
    props
  }

  def getJaasCFG : String = {
    val username = "s78m90xp"
    val password = "JsAkTX90KYhpkceLUY32PxnLtfcjURIk"
    s"org.apache.kafka.common.security.scram.ScramLoginModule required username=$username password=$password;"
  }

}
