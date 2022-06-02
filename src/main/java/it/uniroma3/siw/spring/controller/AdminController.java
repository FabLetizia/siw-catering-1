package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.spring.model.Buffet;
import it.uniroma3.siw.spring.model.Chef;
import it.uniroma3.siw.spring.model.Ingrediente;
import it.uniroma3.siw.spring.model.Piatto;
import it.uniroma3.siw.spring.service.BuffetService;

@Controller
public class AdminController {
	
	
	
	@GetMapping("/indexPiatto")
	public String getindexPiatto(Model model) {
		model.addAttribute("piatto",new Piatto());
		return "admin/piatto/indexPiatto.html";
		
	}
	
	

}
