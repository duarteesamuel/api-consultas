package com.duartetech.api_consultas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duartetech.api_consultas.entities.Doctor;
import com.duartetech.api_consultas.repositories.DoctorRepository;

@Service
public class DoctorService {
	
	@Autowired
	private  DoctorRepository doctorRepository;
	
	public void doctorRegistration(Doctor doctor) {
		doctorRepository.save(doctor);
	}
	
	
}
