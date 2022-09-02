package com.rps.admin.dto;

import java.util.List;

public class SearchResultsDto {

	private int pageNumber;
	private int totalCount;
	private int totalPage;
	private int pageSize;
	private List<BookingDetailsDto> bookingDetails;

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<BookingDetailsDto> getBookingDetails() {
		return bookingDetails;
	}

	public void setBookingDetails(List<BookingDetailsDto> bookingDetails) {
		this.bookingDetails = bookingDetails;
	}

}
