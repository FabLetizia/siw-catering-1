package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.spring.service.BuffetService;
import it.uniroma3.siw.spring.service.ChefService;

@Controller
public class IndexController {

	@Autowired
	private ChefService chefService;
	
	@Autowired
	private BuffetService buffetService;
	
	
	@GetMapping("/")
	public String getBuffetsEChef(Model model) {
		model.addAttribute("tuttiGliChef", chefService.findAll());
		model.addAttribute("tuttiIBuffet", buffetService.findAll());
		return "/index";
	}
	
}
