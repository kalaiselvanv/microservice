package com.rps.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rps.booking.dto.BookingDetailsDto;
import com.rps.booking.dto.SearchRequestDto;
import com.rps.booking.dto.SearchResultsDto;
import com.rps.booking.service.BookingService;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@PostMapping
	public ResponseEntity<BookingDetailsDto> addBooking(@RequestBody BookingDetailsDto details) {
		return new ResponseEntity(bookingService.addBooking(details), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Boolean> updateBooking(@RequestBody BookingDetailsDto details) {
		return new ResponseEntity(bookingService.updateBooking(details), HttpStatus.OK);
	}

	@PostMapping("/search")
	public ResponseEntity<SearchResultsDto> searchUserBookings(@RequestBody SearchRequestDto searchRequest) {
		searchRequest.setClientRole(true);
		return new ResponseEntity(bookingService.getUserBookingHistory(searchRequest), HttpStatus.OK);
	}

	@PostMapping("/admin/{date}")
	public ResponseEntity<Boolean> updateBookingStatus(@PathVariable String date) {
		return new ResponseEntity(bookingService.updateBookingStatus(date), HttpStatus.OK);
	}

}
