package com.rps.gateway.exception;

import java.util.Date;

public class ErrorResponse {

	private Date date;
	private String message;
	private int errorCode;
	private String errorDetails;

	public ErrorResponse(Date date, String message, int errorCode, String errorDetails) {
		super();
		this.date = date;
		this.message = message;
		this.errorCode = errorCode;
		this.errorDetails = errorDetails;
	}

	public ErrorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}

}
