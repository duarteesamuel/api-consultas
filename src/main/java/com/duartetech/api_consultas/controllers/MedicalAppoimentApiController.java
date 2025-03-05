package com.duartetech.api_consultas.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duartetech.api_consultas.controllers.dto.MedicalAppoimentRequestDTO;
import com.duartetech.api_consultas.entities.MedicalAppoiment;
import com.duartetech.api_consultas.services.MedicalAppoimentService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/appoiments")
@RequiredArgsConstructor
public class MedicalAppoimentApiController {
	
	private final MedicalAppoimentService medicalAppoimentService;
	
	
	@PostMapping
	@Operation(
			summary = "Schedule a new doctor appointment",
			description = "...")
	public ResponseEntity<?> registerAppoiment(@RequestBody @Valid MedicalAppoimentRequestDTO dto){
		medicalAppoimentService.registerAppoiment(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}
	
	@GetMapping
	public ResponseEntity<List<MedicalAppoiment>> getAllMedicalAppoiments(){
		
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(medicalAppoimentService.getAllMedicalAppoiments());
	}

}
