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

@ControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleValidationExceptions(
    		MethodArgumentNotValidException e){
		/*Responsible method for providing a concise response regarding
		 *  errors that have been found
		 */
		
		List<String> errors = new ArrayList<>();
		
		e.getBindingResult().getAllErrors().forEach(error -> {
			String errorMessage = error.getDefaultMessage();
			errors.add(errorMessage);
		});
		
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CrmAlreadyExistsException.class)
	public ResponseEntity<Object> handleCrmAlreadyExistsException(
			CrmAlreadyExistsException e){
		/*Responsible method for launching a concise response if a CRM
		 *  is already registered
		 */
		
		Map<String, Object> body = new LinkedHashMap<>();
		
		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.CONFLICT);
		body.put("error", "Crm already exists");
		body.put("message", e.getMessage());
		
		return new ResponseEntity<>(body, HttpStatus.CONFLICT);
		
	}
	
	
	@ExceptionHandler(CpfAlreadyExistsException.class)
	public ResponseEntity<Object> handleCpfAlreadyExistsException(
			CpfAlreadyExistsException e){
		/*Responsible method for launching a concise response if a CPF
		 *  is already registered
		 */
		
		Map<String, Object> body = new LinkedHashMap<>();
		
		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.CONFLICT);
		body.put("error", "Cpf already exists");
		body.put("message", e.getMessage());
		
		return new ResponseEntity<>(body, HttpStatus.CONFLICT);
	}
	
	
	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<Object> handleEmailAlreadyExistsException(
			EmailAlreadyExistsException e){
		/*Responsible method for launching a concise response if a E-mail
		 *  is already registered
		 */
		
		Map<String, Object> body = new LinkedHashMap<>();
		
		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.CONFLICT);
		body.put("error", "Email already exists");
		body.put("error", e.getMessage());
		
		return new ResponseEntity<>(body, HttpStatus.CONFLICT);
	}
	
	
	@ExceptionHandler(TelephoneAlreadyExistsException.class)
	public ResponseEntity<Object> handleTelephoneAlreadyExistsException(
			TelephoneAlreadyExistsException e){
		/*Responsible method for launching a concise response if a Telephone
		 *  is already registered
		 */
		
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.CONFLICT);
		body.put("error", "Telephone already exists");
		body.put("message", e.getMessage());
		
		return new ResponseEntity<>(body, HttpStatus.CONFLICT);
	}

	
	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<Object> handleDataNotFoundException(
			DataNotFoundException e){
		
		/* Responsible method for informing if the database is empty*/
		
		Map<String, Object> body = new LinkedHashMap<>();
		
		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.NOT_FOUND);
		body.put("error", "No data found");
		body.put("message", e.getMessage());
		
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
}
