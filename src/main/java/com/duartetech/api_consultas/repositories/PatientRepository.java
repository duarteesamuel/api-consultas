package com.duartetech.api_consultas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duartetech.api_consultas.entities.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{
	
	boolean existsByCpf(String cpf);
	boolean existsByEmail(String email);
	boolean existsByTelephone(String telephone);
}
