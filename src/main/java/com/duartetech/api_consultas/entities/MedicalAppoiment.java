package com.duartetech.api_consultas.entities;

import java.time.LocalDate;
import java.util.UUID;

import com.duartetech.api_consultas.entities.enums.AppoimentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "appoiment_medical")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MedicalAppoiment {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.UUID)
	@Column(name = "id_appoiment")
	private UUID id;
	
	@FutureOrPresent(message = "The schedule a medical appointment cannot be in the past")
	@Column(name = "appoiment_date", nullable = false)
	private LocalDate date;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	@Column(name = "appoiment_time", nullable = false)
	private String time;
	
	@ManyToOne
	@JoinColumn(name = "id_patient", nullable = false)
	private Patient patient;
	
	@ManyToOne
	@JoinColumn(name = "id_doctor", nullable = false)
	private Doctor doctor;
	
	@Enumerated(EnumType.STRING)
	private AppoimentStatus status;
	
	public MedicalAppoiment(LocalDate date, String time, Patient patient, Doctor doctor) {
		this.date = date;
		this.time = time;
		this.patient = patient;
		this.doctor = doctor;
		this.status = AppoimentStatus.SCHEDULED;
	}
}
