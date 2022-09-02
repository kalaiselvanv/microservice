package com.rps.booking.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;
import com.rps.booking.dto.BookingDetailsDto;
import com.rps.booking.dto.SearchRequestDto;
import com.rps.booking.dto.SearchResultsDto;
import com.rps.booking.entity.BookingDetails;
import com.rps.booking.exception.ApplicationException;
import com.rps.booking.exception.ErrorCodes;
import com.rps.booking.repository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {

	private static final Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	ModelMapper modelMapper;

	public BookingDetailsDto addBooking(BookingDetailsDto details) {

		try {
			BookingDetails bookingDetails = modelMapper.map(details, BookingDetails.class);
			bookingDetails.setPrefix('B');
			bookingDetails.setBookingDate(new Date());
			bookingDetails.setStatus("Pending");
			bookingRepository.addBooking(bookingDetails);
		} catch (Exception e) {
			logger.info("Exception in addBooking {}", e);
			throw new ApplicationException(ErrorCodes.EXCEPTION);
		}
		return details;

	}

	public boolean updateBooking(BookingDetailsDto details) {
		boolean status = false;
		try {
			BookingDetails bookingDetails = modelMapper.map(details, BookingDetails.class);
			bookingDetails.setPrefix('B');
			bookingDetails.setBookingDate(new Date());
			status = bookingRepository.updateBooking(bookingDetails);
		} catch (Exception e) {
			logger.info("Exception in updateBooking {}", e);
			throw new ApplicationException(ErrorCodes.EXCEPTION);
		}
		return status;
	}

	public SearchResultsDto getUserBookingHistory(SearchRequestDto searchRequestDto) {
		List<BookingDetailsDto> bookingDetails = new ArrayList<BookingDetailsDto>();
		SearchResultsDto searchResultsDto = new SearchResultsDto();
		try {

			Type listType = new TypeToken<List<BookingDetailsDto>>() {
			}.getType();
			List<BookingDetails> entityList = bookingRepository.getUserBookingHistory(searchRequestDto);
			bookingDetails = modelMapper.map(entityList, listType);
			searchResultsDto.setBookingDetails(bookingDetails);
			searchResultsDto.setTotalCount(searchRequestDto.getTotalCount());
		} catch (Exception e) {
			logger.info("Exception in getUserBookingHistory {}", e);
			throw new ApplicationException(ErrorCodes.EXCEPTION);
		}
		return searchResultsDto;
	}

	public boolean updateBookingStatus(String date) {
		boolean status = false;
		try {
			status = bookingRepository.updateBookingStatus(date);
		} catch (Exception e) {
			logger.info("Exception in updateBookingStatus {}", e);
			throw new ApplicationException(ErrorCodes.EXCEPTION);
		}
		return status;
	}

}
