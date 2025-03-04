package com.duartetech.api_consultas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.duartetech.api_consultas.entities.Patient;
import com.duartetech.api_consultas.exceptions.DataNotFoundException;
import com.duartetech.api_consultas.services.PatientService;

@Controller
@RequestMapping(value = "/patients")
public class PatientViewController {

	
	@Autowired
	private PatientService patientService;
	
	@GetMapping
	public String displayPatients(Model model) {
		try {
			List<Patient> patients = patientService.displayPatients();
			model.addAttribute("patients", patients);
		} catch(DataNotFoundException e) {
			model.addAttribute("message", e.getMessage());
		}
		
		return "patients";
	}
}
