package com.rps.admin.exception;

public class ApplicationException extends RuntimeException {

	private int errorCodes;

	public ApplicationException(ErrorCodes errorCodes) {
		super(errorCodes.getErrorMessage());
		this.errorCodes = errorCodes.getErroeCode();
	}

	public ApplicationException(int errorCodes) {
		super();
		this.errorCodes = errorCodes;
	}

	public int getErrorCodes() {
		return errorCodes;
	}

	public void setErrorCodes(int errorCodes) {
		this.errorCodes = errorCodes;
	}

}
