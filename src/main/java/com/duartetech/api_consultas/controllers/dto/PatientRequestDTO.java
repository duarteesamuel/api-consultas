package com.duartetech.api_consultas.controllers.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PatientRequestDTO(
		@NotBlank String name,
		@NotBlank String gender,
		String nationality,
		@NotBlank String cpf,
		@NotNull LocalDate dateOfBirth){

}
