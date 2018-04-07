package com.sunshine.publicserver.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sunshine.publicserver.kafka.impl.KafkaProducer;
import com.sunshine.publicserver.kafka.impl.KafkaProducerBuilder;

@Service
public class SendMsgServiceImpl {

	private static final Logger logger = Logger
			.getLogger(SendMsgServiceImpl.class);
	private static final String SYNC_TOPIC = "dx";
	private static final String kafkaBrokers = "11.251.176.54:9092";

	private KafkaProducer<byte[]> kafkaProducer = KafkaProducerBuilder.custom()
			.setBrokerList(kafkaBrokers).build();

	public void writeBinlog2Kafka() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream sOut = null;
		byte[] bytes = null;
		try {
			sOut = new ObjectOutputStream(out);
			sOut.writeObject("");
			sOut.flush();
			bytes = out.toByteArray();
			String key = String.valueOf(System.currentTimeMillis());
			if (bytes != null) {
				long begin = System.currentTimeMillis();
				kafkaProducer.send(SYNC_TOPIC, key, bytes);
				long end = System.currentTimeMillis();
			}
		} catch (Exception e) {
			logger.error("error occurs when write binlog", e);
		} finally {
			try {
				out.close();
			} catch (IOException e) {
			}
			if (sOut != null) {
				try {
					sOut.close();
				} catch (IOException e) {
				}
			}

		}

	}
}
