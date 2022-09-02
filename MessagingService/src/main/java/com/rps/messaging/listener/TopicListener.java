package com.rps.messaging.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.rps.messaging.service.MessagingServiceImpl;

@Service
public class TopicListener {

	private static final Logger logger = LoggerFactory.getLogger(TopicListener.class);

	public TopicListener() {
		super();
	}

	@Autowired
	MessagingServiceImpl messagingServiceImpl;

	@Value("${topic.name.consumer")
	private String topicName;

	@KafkaListener(topics = "${topic.name.consumer}", groupId = "group_id")
	public void consume(ConsumerRecord<String, String> payload) {
		logger.info("Order: {}", payload.value());
		messagingServiceImpl.updateBookingStatus(payload.value());
	}

}