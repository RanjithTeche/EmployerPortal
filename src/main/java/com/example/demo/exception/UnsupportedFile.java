package com.example.demo.exception;

public class UnsupportedFile extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnsupportedFile() {
		super();
	}

	public UnsupportedFile(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UnsupportedFile(String message, Throwable cause) {
		super(message, cause);
	}

	public UnsupportedFile(String message) {
		super(message);
	}

	public UnsupportedFile(Throwable cause) {
		super(cause);
	}

}
