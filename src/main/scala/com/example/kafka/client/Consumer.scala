package com.example.kafka.client

import org.apache.kafka.clients.consumer.KafkaConsumer

import scala.jdk.CollectionConverters._

object Consumer extends App {

  val consumer = new KafkaConsumer(Config.getConsumerConfig)
  val topics = List("learning-topic")
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
