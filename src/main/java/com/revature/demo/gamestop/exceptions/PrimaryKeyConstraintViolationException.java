package com.revature.demo.gamestop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST)
public class PrimaryKeyConstraintViolationException extends RuntimeException{

	private static final long serialVersionUID = 1295097872568685653L;

	public PrimaryKeyConstraintViolationException() {
		super();
	}
	
	public PrimaryKeyConstraintViolationException(String message) {
		super(message);
	}
	
	public PrimaryKeyConstraintViolationException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
