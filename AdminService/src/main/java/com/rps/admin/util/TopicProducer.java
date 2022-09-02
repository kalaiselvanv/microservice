package com.rps.admin.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.rps.admin.exception.ApplicationException;
import com.rps.admin.exception.ErrorCodes;

@Service
public class TopicProducer {

	private static final Logger logger = LoggerFactory.getLogger(TopicProducer.class);
	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	public TopicProducer() {
		super();
	}

	@Value("${topic.name.producer}")
	private String topicName;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void send(String message) {
		try {
			//String date = formatter.format(message);
			Date newDate = formatter.parse(message+"");
			logger.info("Payload enviado: {}", newDate);
			kafkaTemplate.send(topicName, message);
		} catch (Exception e) {
			logger.info("Exception in updateBookingStatus {}", e);
			throw new ApplicationException(ErrorCodes.EXCEPTION);
		}
	}

}