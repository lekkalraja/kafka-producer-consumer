package com.example.kafka.client

import java.util.Properties

import org.apache.kafka.clients.consumer.{ConsumerConfig, KafkaConsumer}
import org.apache.kafka.common.serialization.Serdes

import scala.jdk.CollectionConverters._

object Consumer extends App {

  val consumer = new KafkaConsumer(Config.getConsumerConfig)
  val topics = List("s78m90xp-workd-count-input")
  try {
    consumer.subscribe(topics.asJava)
    while (true) {
      val records = consumer.poll(100)
      for (record <- records.asScala) {
        println(s"Topic: ${record.topic()}, Key: ${record.key()}, Value: ${record.value()}, Offset: ${record.offset()}, Partition: ${record.partition()}")
      }
    }
  }catch{
    case e:Exception => e.printStackTrace()
  }finally {
    consumer.close()
  }
  Runtime.getRuntime.addShutdownHook(new Thread(() => consumer.close()))
}
