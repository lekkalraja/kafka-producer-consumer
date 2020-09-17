package com.example.kafka.client

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

object Producer extends App {

  val producer = new KafkaProducer[String, String](Config.getProducerConfig)
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
  Runtime.getRuntime.addShutdownHook(new Thread(() => producer.close()))
}
