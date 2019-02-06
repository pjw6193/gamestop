package com.revature.demo.gamestop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST)
public class IllegalRecordUpdateException extends RuntimeException {

	private static final long serialVersionUID = 5612407101386203240L;

	public IllegalRecordUpdateException() {
		super();
	}
	
	public IllegalRecordUpdateException(String message) {
		super(message);
	}
	
	public IllegalRecordUpdateException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
