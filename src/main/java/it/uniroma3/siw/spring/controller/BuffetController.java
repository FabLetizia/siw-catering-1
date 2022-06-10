package it.uniroma3.siw.spring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.spring.model.Buffet;
import it.uniroma3.siw.spring.service.BuffetService;
import it.uniroma3.siw.spring.service.ChefService;
import it.uniroma3.siw.spring.service.PiattoService;
import it.uniroma3.siw.spring.validator.BuffetValidator;

@Controller
public class BuffetController {

	@Autowired
	private BuffetValidator buffetValidator;

	@Autowired
	private BuffetService buffetService;

	@Autowired
	private PiattoService piattoService;

	@Autowired
	private ChefService chefService;

	@GetMapping("/buffet/{id}")
	public String getBuffet(@PathVariable("id") Long id, Model model) {
		model.addAttribute("buffet", buffetService.findById(id));
		model.addAttribute("tuttiGliChef", chefService.findAll());
		model.addAttribute("tuttiIBuffet", buffetService.findAll());
		return "user/buffet.html";
	}
	
	@PostMapping("/addBuffet")
	public String addBuffet(@Valid @ModelAttribute("buffet") Buffet buffet, BindingResult bindingResults, Model model) {
		buffetValidator.validate(buffet, bindingResults);
		if(!bindingResults.hasErrors()) {
			buffetService.aggiungiBuffet(buffet);
			model.addAttribute("buffet", buffet);
			return "redirect:/indexBuffet";
		}
		else {
			model.addAttribute("tuttiGliChef", chefService.findAll());
			model.addAttribute("tuttiIPiatti", piattoService.findAll());
			return "admin/buffet/buffetForm.html";
		} 
	}

	@GetMapping("/buffetForm")
	public String getBuffetForm(Model model) {
		model.addAttribute("buffet",new Buffet());
		model.addAttribute("tuttiGliChef", chefService.findAll());
		model.addAttribute("tuttiIPiatti", piattoService.findAll());
		return "admin/buffet/buffetForm.html";

	}

	//	//richiede tutte le persone
	//	@GetMapping("/buffets")
	//	public String getBuffets( Model model) {
	//		List<Buffet> buffets = buffetService.findAll();
	//		model.addAttribute("buffets", buffets);
	//		return "admin/buffet/indexBuffet.html";
	//	}

	@GetMapping("/indexBuffet")
	public String getindexBuffet(Model model) {
		model.addAttribute("buffets", buffetService.findAll());
		return "admin/buffet/indexBuffet.html";

	}

	@GetMapping("/cancellaBuffet/{id}")
	public String removeBuffet(@PathVariable("id") Long id, Model model) {
		buffetService.remove(id);
		return "redirect:/indexBuffet";
	}

	@GetMapping("/mostraBuffet/{id}")
	public String showBuffet(@PathVariable("id") Long id, Model model) {
		model.addAttribute("buffet", buffetService.findById(id));
		return "admin/buffet/mostraBuffet.html";
	}

	@GetMapping("/modificaBuffet/{id}")
	public String modificaBuffet(@PathVariable("id") Long id, Model model) {
		model.addAttribute("buffet", buffetService.findById(id));
		return "admin/buffet/modificaBuffet.html";
	}

	@GetMapping("/indexPiatto/{id}")
	public String getindexPiatto(@PathVariable("id") Long id, Model model) {
		model.addAttribute("piatti", buffetService.findById(id).getPiatti());
		model.addAttribute("buffet_id", id);
		return "admin/piatto/indexPiattoInBuffet.html";

	}

}
