package com.example.kafka.client

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}

object Producer extends App {

  val props:Properties = getConfig
  val producer = new KafkaProducer[String, String](props)
  val topic = "s78m90xp-workd-count-input"
  try {
    for (course <- Array("Scala", "Spark", "Akka", "Play")) {
      val record = new ProducerRecord[String, String](topic, course, s"I am Learning $course")
      val metadata = producer.send(record)
      println(s"sent record(key=${record.key()} value=${record.value()}) meta(partition=${metadata.get().partition()}, offset=${metadata.get().offset()})")
    }
  }catch{
    case e:Exception => e.printStackTrace()
  }finally {
    producer.close()
  }

  def getConfig: Properties = {
    val username = "s78m90xp"
    val password = "JsAkTX90KYhpkceLUY32PxnLtfcjURIk"
    val jaasTemplate = "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"%s\" password=\"%s\";"
    val jaasCfg = String.format(jaasTemplate, username, password)

    val props = new Properties
    props.put("bootstrap.servers", "tricycle-01.srvs.cloudkafka.com:9094,tricycle-02.srvs.cloudkafka.com:9094,tricycle-03.srvs.cloudkafka.com:9094")
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
    props.put("security.protocol", "SASL_SSL")
    props.put("sasl.mechanism", "SCRAM-SHA-256")
    props.put("sasl.jaas.config", jaasCfg)
    props
  }
}
