package com.ivl.movierating.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class MovieExceptionHandler {

	/*
	 * private String INCORRECT_REQUEST = "Not found"; private String BAD_REQUEST =
	 * "BAD_REQUEST";
	 */
	@ExceptionHandler(RecordNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(RecordNotFoundException ex,
			WebRequest request) {

		ExceptionResponse error = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
//return new ResponseEntity<>(new Rating(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
		ExceptionResponse error = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
