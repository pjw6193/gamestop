package com.example.demo.gamestop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST)
public class GameUnavailableForPurchaseException extends RuntimeException{

	private static final long serialVersionUID = 5446213556288319111L;

	public GameUnavailableForPurchaseException() {
		super();
	}
	
	public GameUnavailableForPurchaseException(String message) {
		super(message);
	}
	
	public GameUnavailableForPurchaseException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
