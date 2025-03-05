package com.duartetech.api_consultas.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duartetech.api_consultas.entities.MedicalAppoiment;

@Repository
public interface MedicalAppoimentRepository extends JpaRepository<MedicalAppoiment, UUID>{

}
