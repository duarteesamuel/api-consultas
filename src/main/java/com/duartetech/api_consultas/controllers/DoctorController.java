package com.duartetech.api_consultas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public ResponseEntity<String> registerDoctor(
			 @RequestBody @Valid Doctor doctor){
		
		doctorService.registerDoctor(doctor);
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body("Doctor has been successfully registered.");
	}
	
	@GetMapping
	public ResponseEntity<List<Doctor>> displayDoctors(){
			return ResponseEntity.status(HttpStatus.OK)
					.body(doctorService.displayDoctors());
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<String> updateDoctor(
			 @PathVariable Long id, @RequestBody @Valid Doctor updatedDoctor){
		
		doctorService.updateDoctor(id, updatedDoctor);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body("Doctor data with id " + id + " was updated successfully.");
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> deleteDoctor(@PathVariable Long id){
		doctorService.deleteDoctor(id);
		
		return ResponseEntity.status(HttpStatus.OK)
				.body("Doctor with ID " + id + " has been deactivated.");
	}
	
}
