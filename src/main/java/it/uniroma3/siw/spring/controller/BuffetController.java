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

	@GetMapping("/user/buffet/{id}")
	public String getBuffet(@PathVariable("id") Long id, Model model) {
		model.addAttribute("buffet", buffetService.findById(id));
		model.addAttribute("tuttiGliChef", chefService.findAll());
		model.addAttribute("tuttiIBuffet", buffetService.findAll());
		return "user/buffet.html";
	}
	
	@PostMapping("/admin/addBuffet")
	public String addBuffet(@Valid @ModelAttribute("buffet") Buffet buffet, BindingResult bindingResults, Model model) {
		buffetValidator.validate(buffet, bindingResults);
		if(!bindingResults.hasErrors()) {
			buffetService.aggiungiBuffet(buffet);
			model.addAttribute("buffet", buffet);
			return "redirect:/admin/indexBuffet";
		}
		else {
			model.addAttribute("tuttiGliChef", chefService.findAll());
			model.addAttribute("tuttiIPiatti", piattoService.findAll());
			return "admin/buffet/buffetForm.html";
		} 
	}

	@GetMapping("/admin/buffetForm")
	public String getBuffetForm(Model model) {
		model.addAttribute("buffet",new Buffet());
		model.addAttribute("tuttiGliChef", chefService.findAll());
		model.addAttribute("tuttiIPiatti", piattoService.findAll());
		return "admin/buffet/buffetForm.html";

	}

	@GetMapping("/admin/indexBuffet")
	public String getindexBuffet(Model model) {
		model.addAttribute("buffets", buffetService.findAll());
		return "admin/buffet/indexBuffet.html";

	}

	@GetMapping("/admin/cancellaBuffet/{id}")
	public String removeBuffet(@PathVariable("id") Long id, Model model) {
		buffetService.remove(id);
		return "redirect:/admin/indexBuffet";
	}

	@GetMapping("/admin/mostraBuffet/{id}")
	public String showBuffet(@PathVariable("id") Long id, Model model) {
		model.addAttribute("buffet", buffetService.findById(id));
		return "admin/buffet/mostraBuffet.html";
	}

	@GetMapping("/admin/modificaBuffet/{id}")
	public String modificaBuffet(@PathVariable("id") Long id, Model model) {
		model.addAttribute("buffet", buffetService.findById(id));
		return "admin/buffet/modificaBuffet.html";
	}

	@GetMapping("/admin/indexPiatto/{id}")
	public String getindexPiatto(@PathVariable("id") Long id, Model model) {
		model.addAttribute("piatti", buffetService.findById(id).getPiatti());
		return "admin/piatto/indexPiattoInBuffet.html";

	}
	
	@GetMapping("/admin/editBuffet/{id}")
	  public String getEditBuffet(@PathVariable("id") Long id, Model model) {
		  model.addAttribute("buffet", buffetService.findById(id));
		  return "admin/buffet/editBuffet.html";
	  }
	
	 @PostMapping("/admin/updateBuffet/{id}")
		public String updateBuffet(@PathVariable Long id, @Valid @ModelAttribute("buffet") Buffet buffet, BindingResult bindingResults, Model model) {
			if(!bindingResults.hasErrors()) {
				Buffet oldBuffet = buffetService.findById(id);
				oldBuffet.setId(buffet.getId());
				oldBuffet.setNome(buffet.getNome());
				oldBuffet.setDescrizione(buffet.getDescrizione());
				buffetService.updateBuffet(oldBuffet);
				model.addAttribute("buffet", oldBuffet);
				return "redirect:/admin/indexAdmin";
			}
			else
				return "admin/buffet/editBuffet.html";
		}

}
