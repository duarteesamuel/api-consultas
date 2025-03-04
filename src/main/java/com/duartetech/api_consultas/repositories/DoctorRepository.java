package com.duartetech.api_consultas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duartetech.api_consultas.entities.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long>{

	//Extra method to check if a crm, email and telephone already exists
	boolean existsByCrm(String crm);
	boolean existsByEmail(String email);
	boolean existsByTelephone(String telephone);
}
