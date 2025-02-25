package com.duartetech.api_consultas.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
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
	
	@NotBlank(message = "The patient's gender cannot be empty")
	private String gender;
	
	@Column(nullable = true)
	private String nationality;
	
	@NotBlank(message = "The patient's cpf cannot be empty")
	@Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF must contain only the numbers")
	@Column(unique = true)
	@Size(min = 14, max = 14, message = "The CPF must only contain numbers")
	private String cpf;
	
	@Column(name = "date_of_birth")
	@NotNull(message = "The patient's date of birth cannot be null")
	@Past(message = "Date of birth must be in the past")
	private LocalDate dateOfBirth;
	
	
	public Patient(String name, String gender, String nationality, String cpf, LocalDate dateOfBirth) {
		this.name = name;
		this.gender = gender;
		this.nationality = nationality;
		this.cpf = cpf;
		this.dateOfBirth = dateOfBirth;
	}
	
	
	@PrePersist //Format the CPF before saving it in the database.
	@PreUpdate //Ensures that the formatting will be applied in updates.
	private void formatCpf() {
		this.cpf = formatCpf(this.cpf);
	}
	
	//adds dots and dashes if the CPF only has numbers.
	private String formatCpf(String cpf) {
		if(cpf != null && cpf.matches("\\d{11}")) {
			return cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
		}
		
		return cpf;
	}
	
}
