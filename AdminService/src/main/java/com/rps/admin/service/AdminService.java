package com.rps.admin.service;

import com.rps.admin.dto.SearchRequestDto;
import com.rps.admin.dto.SearchResultsDto;

public interface AdminService {
	
	public SearchResultsDto getUserBookingHistory(SearchRequestDto searchRequestDto) ;


}
