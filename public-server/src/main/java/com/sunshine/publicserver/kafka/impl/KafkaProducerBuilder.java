package com.sunshine.publicserver.kafka.impl;

import java.security.InvalidParameterException;
import java.util.Properties;

/**
 * Kafka生产者的builder器
 *
 */
public class KafkaProducerBuilder {
  // broker的服务器列表
  private String brokerList;

  // 分区用的类，可以为空
  private String partitionerCls = null;

  // 当buffer满的时候，最大等待时间，如果等待时间超时，会抛异常
  private long maxBlockMs = 60000;

  // 发送延时，客户端为积累lingerMs毫秒的数据再往外发送，这样可以减少发送的请求数，但是会增加单个请求的延时
  private long lingerMs = 0;

  // 默认是等几个broker确认收到消息了，就返回，有1个确认就行了，相当于只要leader收到就好，如果改成0，则相当于leader也不需要确认，发出去就算发出去了
  private int acks = 1;

  // 每个batch的最大大小（单位：bytes），如果超过它了，就在底层发出去了。只有当只有一条记录并该记录非常大时，才可以超过该batchSize
  private int batchSize = 1;

  // buffer大小，默认64MB，发送的消息必须小于它，否则发不出去的
  private long bufferMemory = 1024 * 1024 * 64;

  // 最大的请求大小，默认64MB，发送的消息必须小于它，否则发不出去的
  private long maxRequestSize = 1024 * 1024 * 64;

  // log interval 打印日志的时间间隔, 默认是不打印
  private long logIntervalMs = Long.MAX_VALUE;

  public KafkaProducerBuilder() {

  }

  public static KafkaProducerBuilder custom() {
    return new KafkaProducerBuilder();
  }

  public KafkaProducerBuilder setBrokerList(String brokerList) {
    if (brokerList == null || brokerList.isEmpty()) {
      throw new InvalidParameterException("brokerList can't be empty");
    }
    this.brokerList = brokerList;
    return this;
  }

  public KafkaProducerBuilder setPartitionerClass(String partitionerCls) {
    if (partitionerCls != null && partitionerCls.isEmpty()) {
      this.partitionerCls = null;
    }
    this.partitionerCls = partitionerCls;
    return this;
  }

  public KafkaProducerBuilder setMaxBlockMs(long maxBlockMs) {
    this.maxBlockMs = maxBlockMs;
    return this;
  }

  public KafkaProducerBuilder setLingerMs(long lingerMs) {
    this.lingerMs = lingerMs;
    return this;
  }

  public KafkaProducerBuilder setAcks(int acks) {
    this.acks = acks;
    return this;
  }

  public KafkaProducerBuilder setBatchSize(int batchSize) {
    this.batchSize = batchSize;
    return this;
  }

  public KafkaProducerBuilder setBufferMemory(long bufferMemory) {
    this.bufferMemory = bufferMemory;
    return this;
  }

  public KafkaProducerBuilder setMaxRequestSize(long maxRequestSize) {
    this.maxRequestSize = maxRequestSize;
    return this;
  }

  public KafkaProducer<byte[]> build() {
    if (brokerList == null || brokerList.isEmpty()) {
      throw new RuntimeException("zookeeper can't be empty");
    }
    Properties props = new Properties();
    props.put("bootstrap.servers", brokerList);
    props.put("acks", String.valueOf(acks));
    // props.put("retries", 0);
    props.put("batch.size", String.valueOf(this.batchSize));
    props.put("linger.ms", String.valueOf(this.lingerMs));
    props.put("buffer.memory", String.valueOf(this.bufferMemory));
    props.put("max.block.ms", String.valueOf(maxBlockMs));
    props.put("max.request.size", String.valueOf(maxRequestSize));
    if (partitionerCls != null) {
      props.setProperty("partitioner.class", partitionerCls);
    }
    KafkaProducer<byte[]> producer = new KafkaProducer(props);
    return producer;
  }
}
