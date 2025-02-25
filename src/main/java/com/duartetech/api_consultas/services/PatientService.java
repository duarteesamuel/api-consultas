package com.duartetech.api_consultas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duartetech.api_consultas.entities.Patient;
import com.duartetech.api_consultas.exceptions.CpfAlreadyExistsException;
import com.duartetech.api_consultas.exceptions.DataNotFoundException;
import com.duartetech.api_consultas.repositories.PatientRepository;

import jakarta.transaction.Transactional;

@Service
public class PatientService {
	
	@Autowired
	private PatientRepository patientRepository;
	
	//METHOD CREATE
	@Transactional
	public void registerPatient(Patient patient) {
		if(patientRepository.existsByCpf(patient.getCpf())) {
			throw new CpfAlreadyExistsException("CPF:"+patient.getCpf()+" already exists.");
		}
		if(patient.getNationality() == null || patient.getNationality().isEmpty()) {
			patient.setNationality("Brasileira");
		}
		
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
