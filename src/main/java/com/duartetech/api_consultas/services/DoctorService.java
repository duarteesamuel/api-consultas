package com.duartetech.api_consultas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duartetech.api_consultas.entities.Doctor;
import com.duartetech.api_consultas.entities.enums.Status;
import com.duartetech.api_consultas.exceptions.CrmAlreadyExistsException;
import com.duartetech.api_consultas.exceptions.DoctorException;
import com.duartetech.api_consultas.repositories.DoctorRepository;

import jakarta.transaction.Transactional;

@Service
public class DoctorService {
	
	@Autowired
	private  DoctorRepository doctorRepository;
	
	//METHOD CREATE
	@Transactional
	public void registerDoctor(Doctor doctor) {
		if(doctorRepository.existsByCrm(doctor.getCrm())) {
			throw new CrmAlreadyExistsException("CRM: " + doctor.getCrm() + " is already registered.");
		}
		
		
		doctorRepository.save(doctor);
	}
	
	//METHOD READ
	public List<Doctor> displayDoctors(){
		List<Doctor> doctors = doctorRepository.findAll();
		
		if(doctors.isEmpty()) {
			throw new DoctorException("No doctor registered in the system.");
		}
		
		return doctors;
	}
	
	//METHOD UPDATE
	@Transactional
	public Doctor updateDoctor(Long id, Doctor updatedDoctor) {
		//Responsible method to update all doctor data
		
		Doctor foundDoctor = findDoctorById(id);
		
		foundDoctor.setName(updatedDoctor.getName());
		foundDoctor.setCrm(updatedDoctor.getCrm());
		foundDoctor.setSpecialty(updatedDoctor.getSpecialty());
		
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
						() -> new DoctorException("Doctor with id " + id + " not found."));
	}
	
}
