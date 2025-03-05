package com.duartetech.api_consultas.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duartetech.api_consultas.controllers.dto.DoctorRequestDTO;
import com.duartetech.api_consultas.entities.Doctor;
import com.duartetech.api_consultas.entities.enums.Status;
import com.duartetech.api_consultas.exceptions.DataNotFoundException;
import com.duartetech.api_consultas.exceptions.MultipleConflictException;
import com.duartetech.api_consultas.repositories.DoctorRepository;

import jakarta.transaction.Transactional;

@Service
public class DoctorService {
	
	@Autowired
	private  DoctorRepository doctorRepository;
	
	//METHOD CREATE
	@Transactional
	public void registerDoctor(DoctorRequestDTO dto) {
		
		List<String> errors = new ArrayList<>();
		
		if(doctorRepository.existsByCrm(dto.crm())) {
			errors.add("CRM: " + dto.crm() + " is already registered.");
		}
		if(doctorRepository.existsByEmail(dto.email())) {
			errors.add("E-mail: " + dto.email() + " is already registered.");
		}
		if(doctorRepository.existsByTelephone(dto.telephone())) {
			errors.add("Telephone: " + dto.telephone() + " is already registered.");
		}
		
		if(!errors.isEmpty()) {
			throw new MultipleConflictException(errors);
		}
		
		Doctor doctor = Doctor.builder()
				.name(dto.name())
				.email(dto.email())
				.telephone(dto.telephone())
				.gender(dto.gender())
				.nationality(dto.nationality())
				.dateOfBirth(dto.dateOfBirth())
				.crm(dto.crm())
				.specialty(dto.specialty())
				.build();
				
		doctorRepository.save(doctor);
	}
	
	//METHOD READ
	public List<Doctor> displayDoctors(){
		List<Doctor> doctors = doctorRepository.findAll();
		
		if(doctors.isEmpty()) {
			throw new DataNotFoundException("No doctor registered in the system.");
		}
		
		return doctors;
	}
	
	//METHOD UPDATE
	@Transactional
	public Doctor updateDoctor(Long id, DoctorRequestDTO dto) {
		//Responsible method to update all doctor data
		//Implement changes to the new attributes of the doctor entity
		Doctor foundDoctor = findDoctorById(id);
		
		foundDoctor.setName(dto.name());
		foundDoctor.setEmail(dto.email());
		foundDoctor.setTelephone(dto.telephone());
		foundDoctor.setGender(dto.gender());
		foundDoctor.setNationality(dto.nationality());
		foundDoctor.setCrm(dto.crm());
		foundDoctor.setSpecialty(dto.specialty());
					
		
		return doctorRepository.save(foundDoctor);
	}
	
	//METHOD DELETE
	@Transactional
	public void deleteDoctor(Long id) {
		//Responsible for changing the Doctor's status
		Doctor foundDoctor = findDoctorById(id);
		
		foundDoctor.setStatus(Status.INACTIVE);
		
		doctorRepository.save(foundDoctor);
	}
	
	//METHOD FIND
	public Doctor findDoctorById(Long id) {
		return doctorRepository.findById(id)
				.orElseThrow(
						() -> new DataNotFoundException("Doctor with id " + id + " not found."));
	}
	
}
