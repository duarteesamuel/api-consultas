package com.duartetech.api_consultas.entities;

import java.time.LocalDate;

import com.duartetech.api_consultas.entities.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
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
@Table(name = "doctors")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Doctor {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id_doctor")
		private Long id;
		
		@NotBlank(message = "The doctor's name cannot be empty.")
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
		
		@NotBlank(message = "The doctor's gender cannot be empty")
		private String gender;
		
		private String nationality;
		
		@Column(name = "date_of_birth")
		@NotNull(message = "The doctor's date of birth cannot be null")
		@Past(message = "Date of birth must be in the past")
		private LocalDate dateOfBirth;
		
		@NotBlank(message = "Doctor CRM cannot be empty.")
		@Pattern(regexp = "\\d{6}/[A-Z]{2}", message = "CRM must be in the format 123456/RJ")
		@Column(unique = true)
		private String crm;
		
		@NotBlank(message = "Doctor's specialty cannot be empty.")
		private String specialty;
		
		@Enumerated(EnumType.STRING)
		private Status status = Status.ACTIVE;
		
		public Doctor(String name, String email, String telephone, String gender, String nationality,
				LocalDate dateOfBirth, String crm, String specialty) {
			this.name = name;
			this.email = email;
			this.telephone = telephone;
			this.gender = gender;
			this.nationality = nationality;
			this.dateOfBirth = dateOfBirth;
			this.crm = crm;
			this.specialty = specialty;
			this.status = Status.ACTIVE;
		}
		
		 @PrePersist
		 public void setDefaultNationality() {
		     if (this.nationality == null || this.nationality.trim().isEmpty()) {
		          this.nationality = "Brasileira";
		     }
		 }
		 
}
