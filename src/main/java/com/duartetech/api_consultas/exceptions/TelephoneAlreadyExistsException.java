package com.duartetech.api_consultas.exceptions;

public class TelephoneAlreadyExistsException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public TelephoneAlreadyExistsException(String msg) {
		super(msg);
	}
	
}