package com.duartetech.api_consultas.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "doctors")
public record Doctor (
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id_doctor")
		Long id,
		
		@NotBlank(message = "Doctor's name cannot be empty.")
		String name,
		
		@NotBlank(message = "Doctor CRM cannot be empty.")
		@Size(min = 9, max = 9, message = "The doctor's CRM must be in this format: 123456/RJ.")
		String crm,
		
		@NotBlank(message = "Doctor's specialty cannot be empty.")
		String specialty
		) {
}
