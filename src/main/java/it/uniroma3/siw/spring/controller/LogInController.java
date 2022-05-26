package it.uniroma3.siw.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogInController {
	
	@GetMapping("/indexUser")
	public String getindexUser(Model model) {
		return "user/indexUser.html";
		
	}
	
	@GetMapping("/indexAdmin")
	public String getindexAdmin(Model model) {
		return "admin/indexAdmin.html";
	}

}
