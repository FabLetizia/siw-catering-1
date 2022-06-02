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

@Controller
public class IngredienteController {
	
	@Autowired
	private IngredienteService ingredienteService;
	
//	@Autowired
//	private PiattoValidator piattoValidator;
	
	@PostMapping("/addIngrediente")
	  public String addIngrediente(@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente, BindingResult bindingResults, Model model) {
		  //piattoValidator.validate(piatto,  bindingResults);
		  if(!bindingResults.hasErrors()) {
			  ingredienteService.aggiungiIngrediente(ingrediente);
			  model.addAttribute("ingrediente", ingrediente);
			  
			  /* ORSO */
//			  /* DUBBIO */
//			  List<Ingrediente> ingredienti = new ArrayList<>();
//			  ingredienti = (List<Ingrediente>) model.getAttribute("ingredienti");
//			  ingredienti.add(ingrediente);
//			  model.addAttribute("ingredienti", ingredienti);

			  return "redirect:/indexIngrediente";
		  }
		  return "admin/ingrediente/ingredienteForm.html";
	  }
	
	@GetMapping("/ingredienteForm")
	public String getIngredienteForm(Model model) {
		model.addAttribute("ingrediente",new Ingrediente());
		return "admin/ingrediente/ingredienteForm.html";
	}
	
	@GetMapping("/indexIngrediente")
	public String getindexIngrediente(Model model) {
		model.addAttribute("ingredienti", ingredienteService.findAll());
		return "admin/ingrediente/indexIngrediente.html";
	}

	 @PostMapping("/cancellaIngrediente/{id}")
	  public String removeIngrediente(@PathVariable("id") Long id, Model model) {
		  ingredienteService.remove(id);
		  return "redirect:/indexIngrediente";
	  }
	
	@GetMapping("/mostraIngrediente/{id}")
	  public String showIngrediente(@PathVariable("id") Long id, Model model) {
		  model.addAttribute("ingrediente", ingredienteService.findById(id));
		  return "admin/ingrediente/mostraIngrediente.html";
	  }
	 
	 @GetMapping("/modificaIngrediente/{id}")
	  public String modificaIngrediente(@PathVariable("id") Long id, Model model) {
		  model.addAttribute("ingrediente", ingredienteService.findById(id));
		  return "admin/ingrediente/modificaIngrediente.html";
	  }
	 

}
