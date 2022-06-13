package it.uniroma3.siw.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.model.Credentials;
import it.uniroma3.siw.spring.service.BuffetService;
import it.uniroma3.siw.spring.service.ChefService;
import it.uniroma3.siw.spring.service.CredentialsService;



@Controller
public class IndexController {
	
	@Autowired
	private ChefService chefService;
	
	@Autowired
	private BuffetService buffetService;
	 
	@Autowired private CredentialsService credentialsService;
	
	
	@GetMapping("/user/contatti")
	public String getContatti(Model model) {
		model.addAttribute("tuttiGliChef", chefService.findAll());
		model.addAttribute("tuttiIBuffet", buffetService.findAll());
		return "user/contatti.html";
	}
	
	@GetMapping("/admin/indexAdmin")
	public String getindexAdmin(Model model) {
		return "admin/indexAdmin.html";
	}

	@RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("tuttiGliChef", chefService.findAll());
		model.addAttribute("tuttiIBuffet", buffetService.findAll());
		return "index.html";
	}

	@GetMapping("/admin/default")
	public String getAdminPage(Model model) {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
			return "/admin/indexAdmin.html";
		}
		return "index.html";
	}

	@GetMapping("/login")
	public String getAdminLoginForm(Model model) {
		return "adminLogin.html";
	}

	@RequestMapping(value = {"/logout"}, method = RequestMethod.GET) 
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		SecurityContextHolder.clearContext();
		HttpSession session= request.getSession(false);
		if(session != null)
			session.invalidate();

		return "index.html";
	}

	@PostMapping("/login")
	public String loginAdmin(Model model) {
		return "redirect:/admin/default";
	}
	


}
