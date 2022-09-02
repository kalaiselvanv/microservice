package com.rps.booking.service;

import com.rps.booking.dto.BookingDetailsDto;
import com.rps.booking.dto.SearchRequestDto;
import com.rps.booking.dto.SearchResultsDto;

public interface BookingService {

	public BookingDetailsDto addBooking(BookingDetailsDto details);

	public boolean updateBooking(BookingDetailsDto details);

	public SearchResultsDto getUserBookingHistory(SearchRequestDto searchRequestDto);

	public boolean updateBookingStatus(String date);
}
