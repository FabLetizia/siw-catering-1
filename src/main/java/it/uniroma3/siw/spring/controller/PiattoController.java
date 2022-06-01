package it.uniroma3.siw.spring.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.spring.model.Piatto;
import it.uniroma3.siw.spring.service.PiattoService;


@Controller
public class PiattoController {
	
	@Autowired
	private PiattoService piattoService;
	
//	@Autowired
//	private PiattoValidator piattoValidator;
	
	@PostMapping("/piatto")
	  public String addPiatto(@Valid @ModelAttribute("piatto") Piatto piatto, BindingResult bindingResults, Model model) {
		  //piattoValidator.validate(piatto,  bindingResults);
		  if(!bindingResults.hasErrors()) {
			  piattoService.aggiungiPiatto(piatto);
			  model.addAttribute("piatto", piatto);
			  
			  /* DUBBIO */
			  List<Piatto> piatti = new ArrayList<>();
			  piatti = (List<Piatto>) model.getAttribute("piatti");
			  piatti.add(piatto);
			  model.addAttribute("piatti", piatti);

			  return "redirect:/indexPiatto";
		  }
		  return "admin/piatto/piattoForm.html";
	  }
	
	@GetMapping("/piattoForm")
	public String getPiattoForm(Model model) {
		model.addAttribute("piatto",new Piatto());
		return "admin/piatto/piattoForm.html";	
	}
	
	@GetMapping("/indexpiatto")
	public String getindexPiatto(Model model) {
		model.addAttribute("piatti", piattoService.findAll());
		return "admin/piatto/indexPiatto.html";
	}
	
	 @PostMapping("/cancellaPiatto/{id}")
	  public String removePiatto(@PathVariable("id") Long id, Model model) {
		  piattoService.remove(id);
		  return "redirect:/indexPiatto";
	  }
	
	@GetMapping("/mostraPiatto/{id}")
	  public String showPiatto(@PathVariable("id") Long id, Model model) {
		  model.addAttribute("piatto", piattoService.findById(id));
		  return "admin/piatto/mostraPiatto.html";
	  }
	 
	 @GetMapping("/modificaPiatto/{id}")
	  public String modificaPiatto(@PathVariable("id") Long id, Model model) {
		  model.addAttribute("buffet", piattoService.findById(id));
		  return "admin/piatto/modificaPiatto.html";
	  }
	 
	 

}
