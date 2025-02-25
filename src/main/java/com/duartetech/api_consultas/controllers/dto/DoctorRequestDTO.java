package com.duartetech.api_consultas.controllers.dto;

import jakarta.validation.constraints.NotBlank;

public record DoctorRequestDTO(
		@NotBlank String name,
		@NotBlank String crm,
		@NotBlank String specialty) {
}
