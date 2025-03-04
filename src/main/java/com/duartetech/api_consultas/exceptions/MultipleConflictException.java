package com.duartetech.api_consultas.exceptions;

import java.util.List;

public class MultipleConflictException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private final List<String> errors;
	
	public MultipleConflictException(List<String> errors) {
		super("Multiple conflicts found");
		
		this.errors = errors;
	}
	
	public List<String> getErrors(){
		return errors;
	}

}
