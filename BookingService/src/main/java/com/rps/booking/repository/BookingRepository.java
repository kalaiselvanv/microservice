package com.rps.booking.repository;

import java.util.List;

import com.rps.booking.dto.SearchRequestDto;
import com.rps.booking.entity.BookingDetails;

public interface BookingRepository {

	public BookingDetails addBooking(BookingDetails details);

	public boolean updateBooking(BookingDetails bookingDetails);

	public List<BookingDetails> getUserBookingHistory(SearchRequestDto searchRequestDto);

	public boolean updateBookingStatus(String date);
}
