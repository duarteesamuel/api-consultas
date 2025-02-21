package com.duartetech.api_consultas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.duartetech.api_consultas.entities.Doctor;
import com.duartetech.api_consultas.exceptions.DoctorException;
import com.duartetech.api_consultas.services.DoctorService;

@Controller
@RequestMapping(value = "/doctors")
public class DoctorViewController {
	
	@Autowired
	private DoctorService doctorService;
	
	@GetMapping
	public String displayDoctors(Model model){
		try {
			List<Doctor> doctors = doctorService.displayDoctors();
			model.addAttribute("doctors",doctors);
		} catch(DoctorException e) {
			model.addAttribute("message", e.getMessage());
		}
		
		return "doctors";
	}
	
}
