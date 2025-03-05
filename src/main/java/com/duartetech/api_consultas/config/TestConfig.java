package com.duartetech.api_consultas.config;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.duartetech.api_consultas.entities.Doctor;
import com.duartetech.api_consultas.entities.MedicalAppoiment;
import com.duartetech.api_consultas.entities.Patient;
import com.duartetech.api_consultas.entities.enums.AppoimentStatus;
import com.duartetech.api_consultas.entities.enums.Status;
import com.duartetech.api_consultas.repositories.DoctorRepository;
import com.duartetech.api_consultas.repositories.MedicalAppoimentRepository;
import com.duartetech.api_consultas.repositories.PatientRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@Profile("test")
@RequiredArgsConstructor
public class TestConfig implements CommandLineRunner{

	private final DoctorRepository doctorRepository;
	private final PatientRepository patientRepository;
	private final MedicalAppoimentRepository medicalAppoimentRepository;
	
	public void run(String... args) throws Exception{
		//Register a new Doctor
		Doctor doctor1 = Doctor.builder()
				.name("Samuel Duarte Moreira")
				.email("samuel@email.com")
				.telephone("21960199315")
				.gender("Masculino")
				.nationality(null)
				.dateOfBirth(LocalDate.parse("2000-08-20"))
				.crm("123456/RJ")
				.specialty("Cardiologia")
				.status(Status.ACTIVE)
				.build();
		
		Doctor doctor2 = Doctor.builder()
				.name("José Alves da Silva")
				.email("jose@email.com")
				.telephone("11960199023")
				.gender("Masculino")
				.nationality(null)
				.dateOfBirth(LocalDate.parse("1980-09-15"))
				.crm("123456/SP")
				.specialty("Urologia")
				.status(Status.ACTIVE)
				.build();
		
		Doctor doctor3 = Doctor.builder()
				.name("Gabriela Fontenele Evangelista")
				.email("gabriela@email.com")
				.telephone("21971523416")
				.gender("Feminino")
				.nationality("Americana")
				.dateOfBirth(LocalDate.parse("2003-10-10"))
				.crm("234567/RJ")
				.specialty("Radiologia")
				.status(Status.ACTIVE)
				.build();
		
		
		doctorRepository.saveAll(Arrays.asList(doctor1, doctor2, doctor3));
		
		//Register a new patient
		Patient patient1 = Patient.builder()
				.name("João Augusto de Souza")
				.email("joao@email.com")
				.telephone("21970192020")
				.gender("Masculino")
				.nationality(null)
				.cpf("12072353295")
				.dateOfBirth(LocalDate.parse("1999-10-08"))
				.build();
		
		Patient patient2 = Patient.builder()
				.name("José Luiz Martins")
				.email("joséluiz@gmail.com")
				.telephone("71960199320")
				.gender("Masculino")
				.nationality(null)
				.cpf("11122233399")
				.dateOfBirth(LocalDate.parse("2010-12-31"))
				.build();
		
		patientRepository.saveAll(Arrays.asList(patient1, patient2));
		
		//Register a new appoiment medical
		MedicalAppoiment appoiment1 = MedicalAppoiment.builder()
				.date(LocalDate.parse("2025-03-05"))
				.time("16:30")
				.doctor(doctor1)
				.patient(patient1)
				.status(AppoimentStatus.SCHEDULED)
				.build();
		
		MedicalAppoiment appoiment2 = MedicalAppoiment.builder()
				.date(LocalDate.parse("2025-03-06"))
				.time("12:30")
				.doctor(doctor3)
				.patient(patient2)
				.status(AppoimentStatus.SCHEDULED)
				.build();
		
		medicalAppoimentRepository.saveAll(Arrays.asList(appoiment1, appoiment2));
	}
}
