package com.petclinic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.petclinic.services.VetService;

@Controller
public class VetController {
	
	private final VetService vetService;
	
	@Autowired
	public VetController(VetService vetService) {
		this.vetService = vetService;
	}

	@GetMapping({"/vets", "/vets/index", "/vets/index.html", "/vets.html"})
	public String listVets(Model model) {
		
		model.addAttribute("vets", vetService.findAll());
		
		return "vets/index";
	}
}
