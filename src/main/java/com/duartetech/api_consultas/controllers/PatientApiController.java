package com.duartetech.api_consultas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duartetech.api_consultas.controllers.dto.PatientRequestDTO;
import com.duartetech.api_consultas.entities.Patient;
import com.duartetech.api_consultas.services.PatientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/patients")
public class PatientApiController {
	
	@Autowired
	private PatientService patientService;
	
	
	@Operation(
			summary = "Register a new patient",
			description = """
					This endpoint registers a new patient in the system.
					The request must include a valid JSON object containing the following fields:
					
					- **name** (String, required): The patient's full name.
					- **email** (String, required): The doctor's unique email.
					- **telephone** (String, required): The doctor's unique telephone.
					- **gender** (String, required): The patient's gender.
					- **nationality** (String, not required): The patient's nationality.
					- **cpf** (String, required): The patient's unique document.
					- **dateOfBirth** (LocalDate, required): The patient's date of birth.
					
					**Notes**
					- The **id** is automatically generated and should not be provide in the request.
					- If the nationality is not provided, it defaults to "Brasileira".
					
					If any required field is missing or invalid, a **400 Bad Request** response will be returned.
					""")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Patient registered."),
			@ApiResponse(responseCode = "400", description = "Invalid request data. Check the provided information."),
			@ApiResponse(responseCode = "409", description = "Attempt to register existing data"),
			@ApiResponse(responseCode = "500", description = "Unexpected error while processing the request.")
	})
	@PostMapping
	public ResponseEntity<?> registerPatient(
			@RequestBody @Valid PatientRequestDTO dto){
		
		patientService.registerPatient(dto);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body("Patient registered successfully.");
	}
	
	@Operation(
			summary = "Show patients registered in the system",
			description = """
					This endpoint returns all patients registered in the system
					
					If the system does not have any patient registered, a 404 not found will be returned.
					""")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Doctors found in the system."),
			@ApiResponse(responseCode = "404", description = "No doctors found in the system."),
			@ApiResponse(responseCode = "500", description = "Unexpected error while processing the request.")
	})
	@GetMapping
	public ResponseEntity<List<Patient>> displayPatients(){
		List<Patient> patients = patientService.displayPatients();
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(patients);
	}
	
	
	@Operation(
		    summary = "Get a patient's details",  
		    description = """
		        This endpoint allows retrieving the details of a specific patient by their unique ID.
		        The data returned includes the doctor's name, gender, nationality, cpf and date of birth.
		        """,  
		    parameters = {
		        @Parameter(name = "id", description = "Patient data using the provided ID", required = true) 
		    })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Doctor details retrieved."),
			@ApiResponse(responseCode = "404", description = "Doctor not found."),
			@ApiResponse(responseCode = "400", description = "Invalid input data."),
			@ApiResponse(responseCode = "500", description = "Unexpected error while processing the request.")
	})
	@GetMapping(value = "/{id}")
	public ResponseEntity<Patient> findPatientById(@PathVariable Long id){
		Patient patient = patientService.findPatientById(id);
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(patient);
	}
}
