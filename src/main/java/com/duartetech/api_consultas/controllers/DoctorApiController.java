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
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/doctors")
public class DoctorApiController {
	
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
	
	@Operation(
			summary = "Show doctors registered in the system",
			description = """
					This endpoint returns all doctors registered in the system, whether with active or inactive status
					
					If the system does not have any doctor registered, a 400 bad request will be returned.
					""")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Doctors found in the system."),
			@ApiResponse(responseCode = "404", description = "No doctors found in the system."),
			@ApiResponse(responseCode = "500", description = "Unexpected error while processing the request.")
	})
	@GetMapping
	public ResponseEntity<List<Doctor>> displayDoctors(){
		
		List<Doctor> doctors = doctorService.displayDoctors();
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(doctors);
		
	}
	
	@Operation(
			summary = "Update a doctor's details",
			description = """
					This endpoint allows updating the details of a specific doctor. 
					You can update the doctor's name, CRM (medical registration number), 
					and specialty by providing the new values in the request body. 
					If a field is not included in the request, it will not be updated.
        
					The doctor data will be updated using the provided ID.
					The ID must correspond to an existing doctor in the system.
					"""
				, parameters = {
						@Parameter(
								name = "id",
								description = "ID of the doctor to be updated", required = true)
						}
			)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Doctor updated."),
			@ApiResponse(responseCode = "404", description = "Doctor not found."),
			@ApiResponse(responseCode = "400", description = "Invalid input data."),
			@ApiResponse(responseCode = "500", description = "Unexpected error while processing the request.")
	})
	@PutMapping(value = "/{id}")
	public ResponseEntity<String> updateDoctor(
			 @PathVariable Long id, @RequestBody @Valid DoctorRequestDTO dto){
		
		Doctor updateDoctor = new Doctor();
		
		updateDoctor.setName(dto.name());
		updateDoctor.setCrm(dto.crm());
		updateDoctor.setSpecialty(dto.specialty());
		
		doctorService.updateDoctor(id, updateDoctor);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body("Doctor data with id " + id + " was updated successfully.");
	}
	
	
	@Operation(
			summary = "Deactivate a doctor's details",
			description = """
	        This endpoint allows deactivating a specific doctor's account by changing their status to 'INACTIVE'. 
	        The doctor's data will remain in the system but will no longer be available in the active list of doctors. 
	        This is typically used when the doctor is no longer available for consultations or is leaving the system temporarily.
	        
	        Note: This does not delete the doctor's data from the database. Only the doctor's status is changed.)
	        """,
	        parameters = {
	        		@Parameter(name = "id", description = "description = \"ID of the doctor to be deactivated\", required = true)")
	        })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Doctor deactivated."),
			@ApiResponse(responseCode = "404", description = "Doctor not found."),
			@ApiResponse(responseCode = "400", description = "Invalid input data."),
			@ApiResponse(responseCode = "500", description = "Unexpected error while processing the request.")
	})
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> deleteDoctor(@PathVariable Long id){
		doctorService.deleteDoctor(id);
		
		return ResponseEntity.status(HttpStatus.OK)
				.body("Doctor with ID " + id + " has been deactivated.");
	}
	
	
	@Operation(
		    summary = "Get a doctor's details",  
		    description = """
		        This endpoint allows retrieving the details of a specific doctor by their unique ID.
		        The data returned includes the doctor's name, CRM, specialty, and status.
		        
		        If the doctor is deactivated (status is INACTIVE), this will still return their data, but 
		        they will no longer be available in the active list of doctors.
		        """,  
		    parameters = {
		        @Parameter(name = "id", description = "ID of the doctor to retrieve", required = true)  // Descrição do parâmetro do ID na URL
		    })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Doctor details retrieved."),
			@ApiResponse(responseCode = "404", description = "Doctor not found."),
			@ApiResponse(responseCode = "400", description = "Invalid input data."),
			@ApiResponse(responseCode = "500", description = "Unexpected error while processing the request.")
	})
	@GetMapping(value = "/{id}")
	public ResponseEntity<Doctor> findDoctorById(@PathVariable Long id){
		
		Doctor doctor = doctorService.findDoctorById(id);
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(doctor);
	}
	
}
