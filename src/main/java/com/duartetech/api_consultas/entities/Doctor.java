package com.duartetech.api_consultas.entities;

import com.duartetech.api_consultas.entities.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "doctors")
@RequiredArgsConstructor
@Getter
@Setter
public class Doctor {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id_doctor")
		private Long id;
		
		@NotBlank(message = "Doctor's name cannot be empty.")
		private String name;
		
		@NotBlank(message = "Doctor CRM cannot be empty.")
		@Size(min = 9, max = 9, message = "The doctor's CRM must be in this format: 123456/RJ.")
		@Column(unique = true)
		private String crm;
		
		@NotBlank(message = "Doctor's specialty cannot be empty.")
		private String specialty;
		
		@Enumerated(EnumType.STRING)
		@Column(nullable = false)
		private Status status = Status.ACTIVE;
		
}
