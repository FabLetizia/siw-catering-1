package it.uniroma3.siw.spring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.spring.model.Piatto;
import it.uniroma3.siw.spring.service.PiattoService;
import it.uniroma3.siw.spring.validator.PiattoValidator;


@Controller
public class PiattoController {
	
	@Autowired
	private PiattoService piattoService;
	
	@Autowired
	private PiattoValidator piattoValidator;
	
	@PostMapping("/piatto")
	  public String addPiatto(@Valid @ModelAttribute("piatto") Piatto piatto, BindingResult bindingResults, Model model) {
		  //piattoValidator.validate(piatto,  bindingResults);
		  if(!bindingResults.hasErrors()) {
			  piattoService.aggiungiPiatto(piatto);
			  model.addAttribute("piatto", piatto);
			  return "piatto.html";
		  }
		  return "piattoForm.html";
	  }
	
	@GetMapping("/piattoForm")
	public String getPiattoForm(Model model) {
		model.addAttribute("piatto",new Piatto());
		return "admin/piatto/piattoForm.html";
		
	}

}
