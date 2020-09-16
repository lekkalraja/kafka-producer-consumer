package com.example.kafka.client

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}

object Producer extends App {

  val props:Properties = getConfig
  val producer = new KafkaProducer[String, String](props)
  val topic = "course_topic"
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
    val props:Properties = new Properties()
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092")
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
    props.put(ProducerConfig.ACKS_CONFIG,"all")
    props
  }
}
