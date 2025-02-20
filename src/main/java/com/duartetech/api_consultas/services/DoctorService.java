package com.duartetech.api_consultas.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duartetech.api_consultas.entities.Doctor;
import com.duartetech.api_consultas.exceptions.CrmAlreadyExistsException;
import com.duartetech.api_consultas.repositories.DoctorRepository;

import jakarta.transaction.Transactional;

@Service
public class DoctorService {
	
	@Autowired
	private  DoctorRepository doctorRepository;
	
	@Transactional
	public void doctorRegistration(Doctor doctor) {
		if(doctorRepository.existsByCrm(doctor.getCrm())) {
			throw new CrmAlreadyExistsException("CRM: " + doctor.getCrm() + " is already registered.");
		}
		
		doctorRepository.save(doctor);
	}
	
	@Transactional
	public void doctorUpdate(Long id) {
		Optional<Doctor> doctorFound = doctorRepository.findById(id);
		
	}
	
}
