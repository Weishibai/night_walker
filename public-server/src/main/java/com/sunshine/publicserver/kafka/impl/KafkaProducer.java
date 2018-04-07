package com.sunshine.publicserver.kafka.impl;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.log4j.Logger;

/**
 * Kafka消息列表的生产者
 *
 */
public class KafkaProducer<T> {
  protected Properties producerConfig;
  protected Producer<String, T> producer;
  private static final Logger logger = Logger.getLogger(KafkaProducer.class);

  private Callback defaultAsyncCallback = new Callback() {
    @Override
    public void onCompletion(RecordMetadata metadata, Exception exception) {
      try {
        if (exception != null) {
          logger.warn("Async send kafka exception", exception);
        } else if (metadata != null) {
          logger.debug("Send " + metadata.topic() + "-" + metadata.partition() + ": "
              + metadata.offset());
        }
      } catch (Throwable t) {
        // 异常回调一定再抛异常了..
        logger.fatal("found exception", t);
      }
    }
  };

  KafkaProducer(Properties config) {
    producerConfig = new Properties();
    for (Map.Entry<Object, Object> entry : config.entrySet()) {
      producerConfig.put(entry.getKey(), entry.getValue());
    }
    String keySerCls = producerConfig.getProperty("key.serializer", null);
    if (keySerCls == null) {
      producerConfig.setProperty("key.serializer",
          "org.apache.kafka.common.serialization.StringSerializer");
    }
    String valueSerCls = producerConfig.getProperty("value.serializer", null);
    if (valueSerCls == null) {
      producerConfig.setProperty("value.serializer",
          "org.apache.kafka.common.serialization.ByteArraySerializer");
    }
    logger.info("Init kafka producer: " + producerConfig);
  }

  // 懒惰连接
  protected void initConnection() {
    if (producer == null) {
      synchronized (this) {
        if (producer == null) {
          producer = new org.apache.kafka.clients.producer.KafkaProducer<>(producerConfig);
        }
      }
    }
  }

  /**
   * 同步发送一个消息到topic中，按key哈希分片，value是消息内容
   * 
   * @param topic
   * @param key
   * @param value
   * @return 返回发送成功的消息个数
   */
  public int send(String topic, String key, T value) {
    try {
      initConnection();
      ProducerRecord<String, T> record = new ProducerRecord<>(topic, null, key, value);
      Future<RecordMetadata> future = this.producer.send(record);
      RecordMetadata recordMetadata = future.get();
      logger.debug("Send " + topic + "-" + recordMetadata.partition() + ": "
          + recordMetadata.offset());
    } catch (Throwable t) {
      logger.error("Send message to topic " + topic + " failed", t);
      return 0;
    }
    return 1;
  }

  /**
   * 异步发送一个消息到topic中，按key哈希分片，value是消息内容
   *
   * @param topic
   * @param key
   * @param value
   * @return 返回发送成功的消息个数
   */
  public Future<RecordMetadata> asyncSend(String topic, String key, T value) {
    initConnection();
    ProducerRecord<String, T> record = new ProducerRecord<>(topic, null, key, value);
    return this.producer.send(record, defaultAsyncCallback);
  }

  /**
   * 异步发送一个消息到topic中，按key哈希分片，value是消息内容
   *
   * @param topic
   * @param partition 指定发送到哪个partition
   * @param key
   * @param value
   * @return 返回发送成功的消息个数
   */
  public Future<RecordMetadata> asyncSend(String topic, Integer partition, String key, T value) {
    initConnection();
    ProducerRecord<String, T> record = new ProducerRecord<>(topic, partition, key, value);
    return this.producer.send(record, defaultAsyncCallback);
  }

  public void close() {
    if (this.producer != null) {
      synchronized (this) {
        if (this.producer != null) {
          this.producer.close();
          this.producer = null;
        }
      }
    }
  }
}
