package com.example.kafka.client

import java.time.Duration
import java.util.Properties

import org.apache.kafka.clients.consumer.{ConsumerConfig, KafkaConsumer}

import scala.jdk.CollectionConverters._

object Consumer extends App {

  val props:Properties = getConfig

  val consumer = new KafkaConsumer(props)
  val topics = List("course_topic")
  try {
    consumer.subscribe(topics.asJava)
    while (true) {
      val records = consumer.poll(new Duration(1,0))
      for (record <- records.asScala) {
        println(s"Topic: ${record.topic()}, Key: ${record.key()}, Value: ${record.value()}, Offset: ${record.offset()}, Partition: ${record.partition()}")
      }
    }
  }catch{
    case e:Exception => e.printStackTrace()
  }finally {
    consumer.close()
  }

  def getConfig : Properties = {
    val props:Properties = new Properties()
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "test")
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092")
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer")
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer")
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "true")
    props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000")
    props
  }
}
