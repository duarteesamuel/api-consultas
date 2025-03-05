package com.duartetech.api_consultas.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.duartetech.api_consultas.controllers.dto.MedicalAppoimentRequestDTO;
import com.duartetech.api_consultas.entities.Doctor;
import com.duartetech.api_consultas.entities.MedicalAppoiment;
import com.duartetech.api_consultas.entities.Patient;
import com.duartetech.api_consultas.exceptions.DataNotFoundException;
import com.duartetech.api_consultas.repositories.DoctorRepository;
import com.duartetech.api_consultas.repositories.MedicalAppoimentRepository;
import com.duartetech.api_consultas.repositories.PatientRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MedicalAppoimentService {
	
	private final MedicalAppoimentRepository medicalAppoimentRepository;
	private final DoctorRepository doctorRepository;
	private final PatientRepository patientRepository;
	
	
	@Transactional
	public void registerAppoiment(MedicalAppoimentRequestDTO dto) {
		
		Doctor doctor = doctorRepository.findById(dto.doctorId())
				.orElseThrow(() -> new DataNotFoundException("Doctor not found"));

		Patient patient = patientRepository.findById(dto.patientId())
				.orElseThrow(() -> new DataNotFoundException("Patient not found"));
		
		MedicalAppoiment appoiment = MedicalAppoiment.builder()
				.date(dto.date())
				.time(dto.time())
				.doctor(doctor)
				.patient(patient)
				.build();
		
		medicalAppoimentRepository.save(appoiment);
	}
	
	public List<MedicalAppoiment> getAllMedicalAppoiments(){
		
		List<MedicalAppoiment> appoiments = medicalAppoimentRepository.findAll();
		
		if(appoiments.isEmpty()) {
			throw new DataNotFoundException("No queries registered");
		}
		
		return appoiments;
	}
	
}
