package com.example.demo.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { EmployeeNotFound.class })
	protected ResponseEntity<Object> handleEmployeeNotFound(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "Given eployee not found";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler(value = { BadCSVFile.class })
	protected ResponseEntity<Object> handleBadCSVFile(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "given file have some issues";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(value = { UnsupportedFile.class })
	protected ResponseEntity<Object> handleUnsupportedFile(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "given file not supported" + ex.getMessage();
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(value = { DateFormatException.class })
	protected ResponseEntity<Object> handleDateFormat(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "given date have some issues" + ex.getMessage();
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
	protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "Illegal Argument Please check request";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
}