package com.duartetech.api_consultas.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
	public ResponseEntity<Object> handleCrmAlreadyExistsException(
			CrmAlreadyExistsException e){
		
		Map<String, Object> body = new LinkedHashMap<>();
		
		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.BAD_REQUEST);
		body.put("error", "Crm already exists");
		body.put("message", e.getMessage());
		
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(CpfAlreadyExistsException.class)
	public ResponseEntity<Object> handleCpfAlreadyExistsException(
			CpfAlreadyExistsException e){
		
		Map<String, Object> body = new LinkedHashMap<>();
		
		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.BAD_REQUEST);
		body.put("error", "Cpf already exists");
		body.put("message", e.getMessage());
		
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
		
	}
	
	
	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<Object> handleDoctorException(
			DataNotFoundException e){
	
		Map<String, Object> body = new LinkedHashMap<>();
		
		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.NOT_FOUND);
		body.put("error", "No data found");
		body.put("message", e.getMessage());
		
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
}
