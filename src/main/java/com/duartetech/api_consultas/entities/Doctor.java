package com.duartetech.api_consultas.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "doctors")
@RequiredArgsConstructor
public class Doctor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_doctor")
	private Long id;
	
	@NotNull
	@NotBlank(message = "Doctor's name cannot be empty.")
	private String name;
	
	@NotNull
	@NotBlank(message = "Doctor CRM cannot be empty.")
	@Size(min = 9, max = 9, message = "The doctor's CRM must be in this format: 123456/RJ.")
	private String crm;
	
	@NotNull
	@NotBlank(message = "Doctor's specialty cannot be empty.")
	private String specialty;
}
