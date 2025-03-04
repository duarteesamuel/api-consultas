package com.duartetech.api_consultas.exceptions;

public class EmailAlreadyExistsException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public EmailAlreadyExistsException(String msg) {
		super(msg);
	}

}
