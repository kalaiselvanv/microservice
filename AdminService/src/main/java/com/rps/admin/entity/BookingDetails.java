package com.rps.admin.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bookingdetails")
public class BookingDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private char prefix;
	private String employeeId;
	private String address;
	private String seatNumber;
	private Integer floorNumber;
	private Date bookingDate;
	private String slot;
	private String status;
	private String userName;

	public BookingDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookingDetails(Integer id, char prefix, String employeeId, String address, String seatNumber,
			Integer floorNumber, Date bookingDate, String slot, String status, String userName) {
		super();
		this.id = id;
		this.prefix = prefix;
		this.employeeId = employeeId;
		this.address = address;
		this.seatNumber = seatNumber;
		this.floorNumber = floorNumber;
		this.bookingDate = bookingDate;
		this.slot = slot;
		this.status = status;
		this.userName = userName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public char getPrefix() {
		return prefix;
	}

	public void setPrefix(char prefix) {
		this.prefix = prefix;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public Integer getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(Integer floorNumber) {
		this.floorNumber = floorNumber;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getSlot() {
		return slot;
	}

	public void setSlot(String slot) {
		this.slot = slot;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
