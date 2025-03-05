package com.duartetech.api_consultas.controllers.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

public record MedicalAppoimentRequestDTO(
		
		
		@NotNull(message = "Date cannot be null")
		@FutureOrPresent(message = "The appointment date cannot be in the past")
		LocalDate date,
		
		@NotNull(message = "Time cannot be null")
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
		String time,
		
		@NotNull(message = "Doctor ID cannot be null")
		Long doctorId,
		
		@NotNull(message = "Patient ID cannot be null")
		Long patientId
		
		)
{}
