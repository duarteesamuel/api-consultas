package com.duartetech.api_consultas.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	
	/*Responsible method for providing a concise response regarding
	 *  errors that have been found
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleValidationExceptions(
    		MethodArgumentNotValidException e){
		
		List<String> errors = new ArrayList<>();
		
		e.getBindingResult().getAllErrors().forEach(error -> {
			String errorMessage = error.getDefaultMessage();
			errors.add(errorMessage);
		});
		
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
	
	/*Responsible method for launching a concise response if a CRM
	 *  is already registered
	 */
	@ExceptionHandler(CrmAlreadyExistsException.class)
	public ResponseEntity<String> handleCrmAlreadyExistsException(
			CrmAlreadyExistsException e, WebRequest request){
		
		String message = e.getMessage();
		
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}
}
