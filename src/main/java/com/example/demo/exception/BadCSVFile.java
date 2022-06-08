package com.example.demo.exception;

public class BadCSVFile extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BadCSVFile() {
		super();
	}

	public BadCSVFile(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BadCSVFile(String message, Throwable cause) {
		super(message, cause);
	}

	public BadCSVFile(String message) {
		super(message);
	}

	public BadCSVFile(Throwable cause) {
		super(cause);
	}

}
