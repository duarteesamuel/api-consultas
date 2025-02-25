package com.duartetech.api_consultas.controllers.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DoctorRequestDTO(
		@NotBlank String name,
		@NotBlank String email,
		@NotBlank String telephone,
		@NotBlank String gender,
		String nationality,
		@NotNull LocalDate dateOfBirth,
		@NotBlank String crm,
		@NotBlank String specialty) {
}
