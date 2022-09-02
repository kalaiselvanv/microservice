package com.rps.admin.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;
import com.rps.admin.dto.BookingDetailsDto;
import com.rps.admin.dto.SearchRequestDto;
import com.rps.admin.dto.SearchResultsDto;
import com.rps.admin.entity.BookingDetails;
import com.rps.admin.exception.ApplicationException;
import com.rps.admin.exception.ErrorCodes;
import com.rps.admin.repository.AdminRepositoryImpl;

@Service
public class AdminServiceImpl implements AdminService{

	private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
	
	@Autowired
	AdminRepositoryImpl adminRepositoryImpl;

	@Autowired
	ModelMapper modelMapper;
	
	public SearchResultsDto getUserBookingHistory(SearchRequestDto searchRequestDto) {
		List<BookingDetailsDto> bookingDetails = new ArrayList<BookingDetailsDto>();
		SearchResultsDto searchResultsDto = new SearchResultsDto();
		try {

			Type listType = new TypeToken<List<BookingDetailsDto>>() {
			}.getType();
			List<BookingDetails> entityList = adminRepositoryImpl.getUserBookingHistory(searchRequestDto);
			bookingDetails = modelMapper.map(entityList, listType);
			searchResultsDto.setBookingDetails(bookingDetails);
			searchResultsDto.setTotalCount(searchRequestDto.getTotalCount());
		} catch (Exception e) {
			logger.info("Exception in getUserBookingHistory {}", e);
			throw new ApplicationException(ErrorCodes.EXCEPTION);
		}
		return searchResultsDto;
	}

	}
