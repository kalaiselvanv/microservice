package com.rps.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rps.admin.dto.SearchRequestDto;
import com.rps.admin.dto.SearchResultsDto;
import com.rps.admin.service.AdminServiceImpl;
import com.rps.admin.util.TopicProducer;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

	@Autowired
	TopicProducer topicProducer;

	@Autowired
	AdminServiceImpl adminServiceImpl;

	@PostMapping("/maintenanence/{date}/")
	public ResponseEntity<String> initateKafa(@PathVariable String date) {
		topicProducer.send(date);
		return new ResponseEntity("Success", HttpStatus.OK);
	}

	@PostMapping("/search")
	public ResponseEntity<SearchResultsDto> searchUserBookings(@RequestBody SearchRequestDto searchRequest) {
		return new ResponseEntity(adminServiceImpl.getUserBookingHistory(searchRequest), HttpStatus.OK);
	}

}
