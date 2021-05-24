package com.khogofinal.services.exceptions;

public class HttpClientErrorException  extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public HttpClientErrorException(String msg) {
		super(msg);
	}
	
	public HttpClientErrorException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
