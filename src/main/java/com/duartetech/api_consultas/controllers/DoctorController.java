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

import com.duartetech.api_consultas.controllers.dto.DoctorRequestDTO;
import com.duartetech.api_consultas.entities.Doctor;
import com.duartetech.api_consultas.services.DoctorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/doctors")
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;
	
	@Operation(
			summary = "Register a new doctor",
			description = """
					This endpoint registers a new doctor in the system.
					The request must include a valid JSON object containing the following fields:
					
					- **name** (String, required): Full name of the doctor.
					- **crm** (String, required): Unique medical license number.
					- **specialty** (String, required): Medical specialty of the doctor.
					
					**Notes**
					- The **id** is automatically generated and should not be provide in the request.
					- The **status** is always set to `ACTIVE`upon registration, so it should not be included in the request.
					
					If any required field is missing or invalid, a **400 Bad Request** response will be returned. 
					""")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Doctor registered."),
			@ApiResponse(responseCode = "400", description = "Invalid request data. Check the provided information."),
			@ApiResponse(responseCode = "500", description = "Unexpected error while processing the request.")
	})
	@PostMapping
	public ResponseEntity<String> registerDoctor(
			@RequestBody @Valid DoctorRequestDTO dto){
		
		Doctor doctor = new Doctor(dto.name(), dto.crm(), dto.specialty());
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
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Doctor> findDoctorById(@PathVariable Long id){
		
		Doctor doctor = doctorService.findDoctorById(id);
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(doctor);
	}
	
}
