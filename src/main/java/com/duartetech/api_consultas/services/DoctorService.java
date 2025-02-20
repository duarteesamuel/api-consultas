package com.duartetech.api_consultas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duartetech.api_consultas.entities.Doctor;
import com.duartetech.api_consultas.exceptions.CrmAlreadyExistsException;
import com.duartetech.api_consultas.exceptions.DoctorException;
import com.duartetech.api_consultas.repositories.DoctorRepository;

import jakarta.transaction.Transactional;

@Service
public class DoctorService {
	
	@Autowired
	private  DoctorRepository doctorRepository;
	
	@Transactional
	public void registrationDoctor(Doctor doctor) {
		if(doctorRepository.existsByCrm(doctor.getCrm())) {
			throw new CrmAlreadyExistsException("CRM: " + doctor.getCrm() + " is already registered.");
		}
		
		doctorRepository.save(doctor);
	}
	
	@Transactional
	public Doctor updateDoctor(Long id, Doctor updatedDoctor) {
		Doctor foundDoctor = doctorRepository.findById(id)
				.orElseThrow(
						() -> new DoctorException("Doctor with id " + id + " not found."));
		
		foundDoctor.setName(updatedDoctor.getName());
		foundDoctor.setCrm(updatedDoctor.getCrm());
		foundDoctor.setSpecialty(updatedDoctor.getSpecialty());
		
		return doctorRepository.save(foundDoctor);
	}
	
}
