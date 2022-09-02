package com.rps.admin.repository;

import java.util.List;

import com.rps.admin.dto.SearchRequestDto;
import com.rps.admin.entity.BookingDetails;

public interface AdminRepository {

	public List<BookingDetails> getUserBookingHistory(SearchRequestDto searchRequestDto) ;

}
