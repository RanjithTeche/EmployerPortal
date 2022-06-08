package com.example.demo.exception;

public class EmployeeNotFound extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmployeeNotFound() {
		super();
	}

	public EmployeeNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EmployeeNotFound(String message, Throwable cause) {
		super(message, cause);
	}

	public EmployeeNotFound(String message) {
		super(message);
	}

	public EmployeeNotFound(Throwable cause) {
		super(cause);
	}

}
