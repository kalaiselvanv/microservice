package com.rps.messaging.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rps.messaging.exception.ApplicationException;
import com.rps.messaging.exception.ErrorCodes;
import com.rps.messaging.repository.MessagingRepositoryImpl;

@Service
public class MessagingServiceImpl implements MessagingService {

	private static final Logger logger = LoggerFactory.getLogger(MessagingServiceImpl.class);
	
	@Autowired
	MessagingRepositoryImpl messagingRepositoryImpl;
	
	public boolean updateBookingStatus(String date) {
		boolean status = false;
		try {
			status = messagingRepositoryImpl.updateBookingStatus(date);
		} catch (Exception e) {
			logger.info("Exception in updateBookingStatus {}", e);
			throw new ApplicationException(ErrorCodes.EXCEPTION);
		}
		return status;
	}
}
