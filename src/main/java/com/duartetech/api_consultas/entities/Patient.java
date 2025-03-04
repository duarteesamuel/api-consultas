package com.duartetech.api_consultas.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "patients")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Patient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_patient")
	private Long id;
	
	@NotBlank(message = "The patient's name cannot be empty")
	@Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
	private String name;
	
	@NotBlank(message = "The doctor's email cannot be empty")
	@Email(message = "The doctor's email must be in this format: name@email.com")
	@Column(unique = true)
	private String email;
	
	@NotBlank(message = "The doctor's email cannot be empty")
	@Size(min = 11, max = 11, message = "The phone must contain only 11 numbers")
	@Column(unique = true)
	private String telephone;
	
	@NotBlank(message = "The patient's gender cannot be empty")
	private String gender;
	
	@Column(nullable = true)
	private String nationality;
	
	@NotBlank(message = "The patient's cpf cannot be empty")
	@Column(unique = true)
	@Size(min = 11, max = 11, message = "The CPF must only contain numbers")
	private String cpf;
	
	@Column(name = "date_of_birth")
	@NotNull(message = "The patient's date of birth cannot be null")
	@Past(message = "Date of birth must be in the past")
	private LocalDate dateOfBirth;

	public Patient(String name,String email,String telephone, String gender,
			String nationality, String cpf, LocalDate dateOfBirth) {
		this.name = name;
		this.email = email;
		this.telephone = telephone;
		this.gender = gender;
		this.nationality = nationality;
		this.cpf = cpf;
		this.dateOfBirth = dateOfBirth;
	}
	
}
