package com.duartetech.api_consultas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duartetech.api_consultas.entities.Doctor;
import com.duartetech.api_consultas.services.DoctorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/doctors")
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;
	
	
	@PostMapping
	public ResponseEntity<String> doctorRegistration(@Valid @RequestBody Doctor doctor){
		doctorService.doctorRegistration(doctor);
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body("Doctor has been successfully registered.");
	}
	
}
