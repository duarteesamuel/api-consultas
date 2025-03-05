package com.duartetech.api_consultas.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duartetech.api_consultas.controllers.dto.PatientRequestDTO;
import com.duartetech.api_consultas.entities.Patient;
import com.duartetech.api_consultas.exceptions.DataNotFoundException;
import com.duartetech.api_consultas.exceptions.MultipleConflictException;
import com.duartetech.api_consultas.repositories.PatientRepository;

import jakarta.transaction.Transactional;

@Service
public class PatientService {
	
	@Autowired
	private PatientRepository patientRepository;
	
	//METHOD CREATE
	@Transactional
	public void registerPatient(PatientRequestDTO dto) {
		
		List<String> errors = new ArrayList<>();
		
		if(patientRepository.existsByCpf(dto.cpf())) {
			errors.add("CPF: "+dto.cpf()+" already exists.");
		}
		if(patientRepository.existsByEmail(dto.email())) {
			errors.add("E-mail: "+dto.email()+ " already exists.");
		}
		if(patientRepository.existsByTelephone(dto.telephone())) {
			errors.add("Telephone: "+dto.telephone()+ " already exists.");
		}
		
		if(!errors.isEmpty()) {
			throw new MultipleConflictException(errors);
		}
		
		Patient patient = Patient.builder()
				.name(dto.name())
				.email(dto.email())
				.telephone(dto.telephone())
				.gender(dto.gender())
				.nationality(dto.nationality())
				.cpf(dto.cpf())
				.dateOfBirth(dto.dateOfBirth())
				.build();
		
		patientRepository.save(patient);
	}
	
	//METHOD READ
	public List<Patient> displayPatients(){
		List<Patient> patients = patientRepository.findAll();
		
		if(patients.isEmpty()) {
			throw new DataNotFoundException("No patient registered in the system.");
		}
		
		return patients;
	}
	
	//METHOD FIND
	public Patient findPatientById(Long id) {
		return patientRepository.findById(id)
				.orElseThrow(
						() -> new DataNotFoundException("Patient with id " + id + " not found."));
	}
	
}
