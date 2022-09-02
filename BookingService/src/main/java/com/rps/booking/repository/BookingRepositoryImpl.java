package com.rps.booking.repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.exception.SQLGrammarException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rps.booking.dto.SearchRequestDto;
import com.rps.booking.entity.BookingDetails;
import com.rps.booking.exception.ApplicationException;
import com.rps.booking.exception.ErrorCodes;

@Repository
public class BookingRepositoryImpl implements BookingRepository {

	private static final Logger logger = LoggerFactory.getLogger(BookingRepositoryImpl.class);
	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	public BookingRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Transactional
	public BookingDetails addBooking(BookingDetails details) {
		try {
			entityManager.persist(details);
			entityManager.flush();
		} catch (SQLGrammarException e) {
			logger.info("Exception in addBooking {}", e);
			throw new ApplicationException(ErrorCodes.SQL_EXCEPTION);
		} catch (Exception e) {
			logger.info("Exception in addBooking {}", e);
			throw new ApplicationException(ErrorCodes.SQL_EXCEPTION);
		}

		return details;
	}

	@Transactional
	public boolean updateBooking(BookingDetails bookingDetails) {
		boolean status = false;
		try {
			entityManager.merge(bookingDetails);
			entityManager.flush();
			status = true;
		} catch (SQLGrammarException e) {
			logger.info("Exception in updateBooking {}", e);
			throw new ApplicationException(ErrorCodes.SQL_EXCEPTION);
		} catch (Exception e) {
			logger.info("Exception in updateBooking {}", e);
			throw new ApplicationException(ErrorCodes.SQL_EXCEPTION);
		}

		return status;
	}

	public List<BookingDetails> getUserBookingHistory(SearchRequestDto searchRequestDto) {
		List<BookingDetails> listBookins = null;
		try {
			List<Predicate> predicateList = new ArrayList<>();

			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<BookingDetails> cq = cb.createQuery(BookingDetails.class);
			Root<BookingDetails> bookDetails = cq.from(BookingDetails.class);
			if (searchRequestDto.isClientRole()) {
				predicateList.add(cb.equal(bookDetails.get("userName"), searchRequestDto.getUserName()));
			}
			if (searchRequestDto.getBookingId() != null && !searchRequestDto.getBookingId().isEmpty()) {
				predicateList.add(cb.equal(bookDetails.get("id"), searchRequestDto.getBookingId().trim().substring(1,
						searchRequestDto.getBookingId().trim().length())));
			}
			if (!searchRequestDto.isClientRole() && searchRequestDto.getBookingDate() != null) {
				String date = formatter.format(searchRequestDto.getBookingDate());
				Date newDate = formatter.parse(date);
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
				predicateList.add(cb.between(bookDetails.get("bookingDate"), startDate, endDate));
			}
			if (!searchRequestDto.isClientRole() && searchRequestDto.getAddress() != null) {
				predicateList.add(cb.like(bookDetails.get("address"), searchRequestDto.getAddress()));
			}
			cq.where(predicateList.toArray(new Predicate[] {}));
			/*TypedQuery<BookingDetails> query = entityManager.createQuery(cq);
			int bookingSize = query.getResultList().size();
			int pageNumber = searchRequestDto.getPageNumber();
			int pageSize = searchRequestDto.getPageSize();
			searchRequestDto.setTotalCount(bookingSize);
			pageNumber = (pageNumber - 1) * pageSize;*/

			TypedQuery<BookingDetails> resultQuery = entityManager.createQuery(cq);//.setFirstResult(pageNumber).setMaxResults(pageSize);
			listBookins = resultQuery.getResultList();

		} catch (SQLGrammarException e) {
			logger.info("Exception in getUserBookingHistory {}", e);
			throw new ApplicationException(ErrorCodes.SQL_EXCEPTION);
		} catch (Exception e) {
			logger.info("Exception in getUserBookingHistory {}", e);
			throw new ApplicationException(ErrorCodes.SQL_EXCEPTION);
		}

		return listBookins;
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
