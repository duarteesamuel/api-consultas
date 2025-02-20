package com.duartetech.api_consultas.exceptions;

public class CrmAlreadyExistsException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public CrmAlreadyExistsException(String message) {
		super(message);
	}

}
