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
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "doctors")
@NoArgsConstructor
@AllArgsConstructor
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
		@Pattern(regexp = "\\d{6}/[A-Z]{2}", message = "CRM must be in the format 123456/RJ")
		@Column(unique = true)
		private String crm;
		
		@NotBlank(message = "Doctor's specialty cannot be empty.")
		private String specialty;
		
		@Enumerated(EnumType.STRING)
		@Column(nullable = false)
		private Status status = Status.ACTIVE;
		
}
