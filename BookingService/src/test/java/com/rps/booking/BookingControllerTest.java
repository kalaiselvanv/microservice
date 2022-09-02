package com.rps.booking;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.test.context.junit4.SpringRunner;

import com.rps.booking.controller.BookingController;
import com.rps.booking.dto.BookingDetailsDto;
import com.rps.booking.repository.BookingRepositoryImpl;
import com.rps.booking.service.BookingService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { BookingController.class })
@MockBeans({ @MockBean(BookingService.class), @MockBean(ModelMapper.class), @MockBean(BookingRepositoryImpl.class),
		@MockBean(EntityManager.class) })
public class BookingControllerTest {

	@Autowired
	BookingService bookingService;

	@Autowired
	EntityManager entityManager;

	@Autowired
	BookingRepositoryImpl bookingRepositoryImpl;

	@Autowired
	ModelMapper modelMapper;

	@SuppressWarnings("deprecation")
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddBooking() {
		BookingDetailsDto details = new BookingDetailsDto();
		details.setAddress("test");
		details.setBookingDate(null);
		details.setEmployeeId("123");
		details.setUserName("kalaiv");
		details.setSlot("9-10");
		details.setFloorNumber(1);
		details.setSeatNumber("123");
		Mockito.when(bookingService.addBooking(details)).thenReturn(details);
		assertNotNull(details);
	}

	@Test
	public void updateAddBooking() {
		BookingDetailsDto details = new BookingDetailsDto();
		boolean status = false;
		details.setId(1);
		details.setAddress("test");
		details.setBookingDate(null);
		details.setEmployeeId("123");
		details.setUserName("kalaiv");
		details.setSlot("9-10");
		details.setFloorNumber(1);
		details.setSeatNumber("123");
		details.setStatus("Confirmed");
		Mockito.when(bookingService.updateBooking(details)).thenReturn(true);
		status = bookingService.updateBooking(details);
		assertNotNull(details);
		assertTrue(status);
	}

	@Test
	public void updateAddBookingNegative() {
		BookingDetailsDto details = new BookingDetailsDto();
		boolean status = false;
		details.setId(111111111);
		details.setAddress("test");
		details.setBookingDate(null);
		details.setEmployeeId("123");
		details.setUserName("kalaiv");
		details.setSlot("9-10");
		details.setFloorNumber(1);
		details.setSeatNumber("123");
		details.setStatus("Confirmed");
		Mockito.when(bookingService.updateBooking(details)).thenReturn(true);
		status = bookingService.updateBooking(details);
		assertNotNull(details);
		assertFalse(status);
	}
}
