package com.lawencon.community.exception;

public class InvalidLoginException extends RuntimeException {
	
	private static final long serialVersionUID = -8080578138310293209L;

	public InvalidLoginException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public InvalidLoginException(String msg) {
		super(msg);
	}
	
	public InvalidLoginException(Throwable cause) {
		super(cause);
	}
}
