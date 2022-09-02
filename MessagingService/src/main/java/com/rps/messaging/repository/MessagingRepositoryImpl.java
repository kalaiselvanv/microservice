package com.rps.messaging.repository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.exception.SQLGrammarException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rps.messaging.entity.BookingDetails;
import com.rps.messaging.exception.ApplicationException;
import com.rps.messaging.exception.ErrorCodes;

@Repository
public class MessagingRepositoryImpl implements MessagingRepository {

	private static final Logger logger = LoggerFactory.getLogger(MessagingRepositoryImpl.class);
	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	public MessagingRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Transactional
	public boolean updateBookingStatus(String dateReq) {
		boolean status = false;
		try {
			Date newDate = formatter.parse(dateReq);
			Calendar calender = Calendar.getInstance();
			calender.setTime(newDate);
			calender.set(Calendar.HOUR_OF_DAY, 0);
			calender.set(Calendar.HOUR, 0);
			calender.set(Calendar.MINUTE, 0);
			calender.set(Calendar.SECOND, 0);
			Date startDate = new Date(calender.getTimeInMillis());
			calender.set(Calendar.HOUR_OF_DAY, 23);
			calender.set(Calendar.HOUR, 23);
			calender.set(Calendar.MINUTE, 59);
			calender.set(Calendar.SECOND, 59);
			Date endDate = new Date(calender.getTimeInMillis());

			CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
			CriteriaUpdate<BookingDetails> update = cb.createCriteriaUpdate(BookingDetails.class);
			Root e = update.from(BookingDetails.class);
			update.set("status", "Cancelled");
			update.where(cb.between(e.get("bookingDate"), startDate, endDate));
			this.entityManager.createQuery(update).executeUpdate();
			status = true;

		} catch (SQLGrammarException e) {
			logger.info("Exception in updateBookingStatus {}", e);
			throw new ApplicationException(ErrorCodes.SQL_EXCEPTION);
		} catch (Exception e) {
			logger.info("Exception in updateBookingStatus {}", e);
			throw new ApplicationException(ErrorCodes.SQL_EXCEPTION);
		}

		return status;
	}

}
