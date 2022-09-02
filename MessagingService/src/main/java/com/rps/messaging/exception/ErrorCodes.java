package com.rps.messaging.exception;

public enum ErrorCodes {

	UNAUTHORIZAED_USER(410, "Invalid User"), UNAUTHORIZAED_TOKEN(411, "Invalid token"), EXCEPTION(412, "Exception"),
	SQL_EXCEPTION(413, "SQL grammer exception");

	private final int erroeCode;
	private final String errorMessage;

	private ErrorCodes(int erroeCode, String errorMessage) {
		this.erroeCode = erroeCode;
		this.errorMessage = errorMessage;
	}

	public int getErroeCode() {
		return erroeCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
