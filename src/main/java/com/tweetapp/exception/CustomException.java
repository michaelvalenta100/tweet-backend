package com.tweetapp.exception;

public class CustomException extends RuntimeException {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	public String message;

	public CustomException(String message) {
		super(message);
		this.message = message;
	}

	public CustomException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomException() {
	}
}
