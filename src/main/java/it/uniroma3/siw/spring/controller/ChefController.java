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

import it.uniroma3.siw.spring.model.Chef;
import it.uniroma3.siw.spring.service.BuffetService;
import it.uniroma3.siw.spring.service.ChefService;
import it.uniroma3.siw.spring.validator.ChefValidator;

@Controller
public class ChefController {
	
	@Autowired
	private ChefService chefService;
	
	@Autowired
	private BuffetService buffetService;
	
	@Autowired
	private ChefValidator chefValidator;
	
	
	@GetMapping("/chef/{id}")
	public String getChef(@PathVariable("id") Long id, Model model) {
		model.addAttribute("chef", chefService.findById(id));
		model.addAttribute("tuttiGliChef", chefService.findAll());
		model.addAttribute("tuttiIBuffet", buffetService.findAll());
		return "user/chef.html";
	}
	
	@PostMapping("/addChef")
	  public String addChef(@Valid @ModelAttribute("chef") Chef chef, BindingResult bindingResults, Model model) {
		  chefValidator.validate(chef,  bindingResults);
		  if(!bindingResults.hasErrors()) {
			  chefService.aggiungiChef(chef);
			  model.addAttribute("chef", chef);

			  return "redirect:/indexChef";
		  }
		  return "admin/chef/chefForm.html";
	  }
	
	@GetMapping("/chefForm")
	public String getChefForm(Model model) {
		model.addAttribute("chef",new Chef());
		return "admin/chef/chefForm.html";
	}
	
	@GetMapping("/indexChef")
	public String getindexChef(Model model) {
		model.addAttribute("chefs", chefService.findAll());
		return "admin/chef/indexChef.html";
	}

	 @GetMapping("/cancellaChef/{id}")
	  public String removeChef(@PathVariable("id") Long id, Model model) {
		  chefService.remove(id);
		  return "redirect:/indexChef";
	  }
	
	@GetMapping("/mostraChef/{id}")
	  public String showChef(@PathVariable("id") Long id, Model model) {
		  model.addAttribute("chef", chefService.findById(id));
		  return "admin/chef/mostraChef.html";
	  }
	 
	 @GetMapping("/modificaChef/{id}")
	  public String modificaChef(@PathVariable("id") Long id, Model model) {
		  model.addAttribute("chef", chefService.findById(id));
		  return "admin/chef/modificaChef.html";
	  }
	 

}
