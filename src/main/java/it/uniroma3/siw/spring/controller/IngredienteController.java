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

import it.uniroma3.siw.spring.model.Ingrediente;
import it.uniroma3.siw.spring.service.IngredienteService;
import it.uniroma3.siw.spring.service.PiattoService;
import it.uniroma3.siw.spring.validator.IngredienteValidator;

@Controller
public class IngredienteController {
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@Autowired
	private PiattoService piattoService;
	
	@Autowired
	private IngredienteValidator ingredienteValidator;
	
	@PostMapping("/admin/addIngrediente")
	  public String addIngrediente(@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente, BindingResult bindingResults, Model model) {
		  ingredienteValidator.validate(ingrediente,  bindingResults);
		  if(!bindingResults.hasErrors()) {
			  ingredienteService.aggiungiIngrediente(ingrediente);
			  model.addAttribute("ingrediente", ingrediente);

			  return "redirect:/admin/indexIngrediente";
		  }
		  return "admin/ingrediente/ingredienteForm.html";
	  }
	
	@PostMapping("/admin/addIngrediente/{id}")
	  public String addIngredienteAPiatto(@PathVariable Long id, @Valid @ModelAttribute("ingrediente") Ingrediente ingrediente, BindingResult bindingResults, Model model) {
		  ingredienteValidator.validate(ingrediente,  bindingResults);
		  if(!bindingResults.hasErrors()) {
			  piattoService.findById(id).getIngredienti().add(ingrediente);
			  piattoService.aggiungiPiatto(piattoService.findById(id));
			  model.addAttribute("ingrediente", ingrediente);
			  return "redirect:/admin/indexBuffet";
		  }
		  return "admin/ingrediente/ingredienteForm.html";
	  }
	
	@GetMapping("/admin/ingredienteForm")
	public String getIngredienteForm(Model model) {
		model.addAttribute("ingrediente",new Ingrediente());
		return "admin/ingrediente/ingredienteForm.html";
	}
	
	@GetMapping("/admin/ingredienteForm/{id}")
	public String getIngredienteFormPerPiatto(@PathVariable Long id, Model model) {
		model.addAttribute("ingrediente",new Ingrediente());
		model.addAttribute("piatto_id",id);
		return "admin/ingrediente/ingredienteFormPerPiatto.html";	
	}
	
	@GetMapping("/admin/indexIngrediente")
	public String getindexIngrediente(Model model) {
		model.addAttribute("ingredienti", ingredienteService.findAll());
		return "admin/ingrediente/indexIngrediente.html";
	}

	 @GetMapping("/admin/cancellaIngrediente/{id}")
	  public String removeIngrediente(@PathVariable("id") Long id, Model model) {
		  ingredienteService.remove(id);
		  return "redirect:/admin/indexIngrediente";
	  }
	
	@GetMapping("/admin/mostraIngrediente/{id}")
	  public String showIngrediente(@PathVariable("id") Long id, Model model) {
		  model.addAttribute("ingrediente", ingredienteService.findById(id));
		  return "admin/ingrediente/mostraIngrediente.html";
	  }
	 
	 @GetMapping("/admin/modificaIngrediente/{id}")
	  public String modificaIngrediente(@PathVariable("id") Long id, Model model) {
		  model.addAttribute("ingrediente", ingredienteService.findById(id));
		  return "admin/ingrediente/modificaIngrediente.html";
	  }
	 
	 @PostMapping("/admin/updateIngrediente/{id}")
		public String updateIngrediente(@PathVariable Long id, @Valid @ModelAttribute("ingrediente") Ingrediente ingrediente, BindingResult bindingResults, Model model) {
			if(!bindingResults.hasErrors()) {
				Ingrediente oldIngrediente = ingredienteService.findById(id);
				oldIngrediente.setId(ingrediente.getId());
				oldIngrediente.setNome(ingrediente.getNome());
				oldIngrediente.setDescrizione(ingrediente.getDescrizione());
				oldIngrediente.setOrigine(ingrediente.getOrigine());
				ingredienteService.updateIngrediente(oldIngrediente);
				model.addAttribute("chef", oldIngrediente);
				return "redirect:/admin/indexAdmin";
			}
			else
				return "admin/ingrediente/modificaIngrediente.html";
		}
	 
}
