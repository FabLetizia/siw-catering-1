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

import it.uniroma3.siw.spring.model.Piatto;
import it.uniroma3.siw.spring.service.BuffetService;
import it.uniroma3.siw.spring.service.ChefService;
import it.uniroma3.siw.spring.service.IngredienteService;
import it.uniroma3.siw.spring.service.PiattoService;
import it.uniroma3.siw.spring.validator.PiattoValidator;


@Controller
public class PiattoController {
	
	@Autowired
	private PiattoService piattoService;
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@Autowired
	private BuffetService buffetService;
	
	@Autowired
	private PiattoValidator piattoValidator;
	
	@Autowired
	private ChefService chefService;
	
	@GetMapping("/user/piatto/{id}")
	public String getPiatto(@PathVariable("id") Long id, Model model) {
		model.addAttribute("piatto", piattoService.findById(id));
		model.addAttribute("tuttiGliChef", chefService.findAll());
		model.addAttribute("tuttiIBuffet", buffetService.findAll());
		return "user/piatto.html";
	}
	
	@PostMapping("/admin/addPiatto")
	  public String addPiatto(@Valid @ModelAttribute("piatto") Piatto piatto, BindingResult bindingResults, Model model) {
		  piattoValidator.validate(piatto, bindingResults);
		  if(!bindingResults.hasErrors()) {
			  piattoService.aggiungiPiatto(piatto);
			  model.addAttribute("piatto", piatto);

			  return "redirect:/admin/indexPiatto";
		  }
		  else {
				model.addAttribute("tuttiGliIngredienti", ingredienteService.findAll());
				return "admin/piatto/piattoForm.html";
		  } 
	  }
	
	@PostMapping("/admin/addPiatto/{id}")
	  public String addPiattoABuffet(@PathVariable Long id, @Valid @ModelAttribute("piatto") Piatto piatto, BindingResult bindingResults, Model model) {
		  piattoValidator.validate(piatto,  bindingResults);
		  if(!bindingResults.hasErrors()) {
			  buffetService.findById(id).getPiatti().add(piatto);
			  buffetService.aggiungiBuffet(buffetService.findById(id));
			  model.addAttribute("piatto", piatto);
			  return "redirect:/admin/indexBuffet";
		  }
		  return "admin/piatto/piattoForm.html";
	  }
	
	@GetMapping("/admin/piattoForm")
	public String getPiattoForm(Model model) {
		model.addAttribute("piatto",new Piatto());
		model.addAttribute("tuttiGliIngredienti", ingredienteService.findAll());
		return "admin/piatto/piattoForm.html";	
	}
	
	@GetMapping("/admin/piattoForm/{id}")
	public String getPiattoFormPerBuffet(@PathVariable Long id, Model model) {
		model.addAttribute("piatto",new Piatto());
		model.addAttribute("buffet_id",id);
		model.addAttribute("tuttiGliIngredienti", ingredienteService.findAll());
		return "admin/piatto/piattoFormPerBuffet.html";	
	}
	
	@GetMapping("/admin/indexPiatto")
	public String getindexPiatto(Model model) {
		model.addAttribute("piatti", piattoService.findAll());
		return "admin/piatto/indexPiatto.html";
	}
	
	 @GetMapping("/admin/cancellaPiatto/{id}")
	  public String removePiatto(@PathVariable("id") Long id, Model model) {
		  piattoService.remove(id);
		  return "redirect:/admin/indexPiatto";
	  }
	
	@GetMapping("/admin/mostraPiatto/{id}")
	  public String showPiatto(@PathVariable("id") Long id, Model model) {
		  model.addAttribute("piatto", piattoService.findById(id));
		  return "admin/piatto/mostraPiatto.html";
	  }
	 
	 @GetMapping("/admin/modificaPiatto/{id}")
	  public String modificaPiatto(@PathVariable("id") Long id, Model model) {
		  model.addAttribute("piatto", piattoService.findById(id));
		  return "admin/piatto/modificaPiatto.html";
	  }
	 
	 @GetMapping("/admin/indexIngrediente/{id}")
		public String getindexIngrediente(@PathVariable("id") Long id, Model model) {
			model.addAttribute("ingredienti", piattoService.findById(id).getIngredienti());
			model.addAttribute("piatto_id", id);
			return "admin/ingrediente/indexIngredienteInPiatto.html";
		}
	 
	 @GetMapping("/admin/editPiatto/{id}")
	  public String getEditPiatto(@PathVariable("id") Long id, Model model) {
		  model.addAttribute("piatto", piattoService.findById(id));
		  return "admin/piatto/editPiatto.html";
	  }
	 
	 @PostMapping("/admin/updatePiatto/{id}")
		public String updatePiatto(@PathVariable Long id, @Valid @ModelAttribute("piatto") Piatto piatto, BindingResult bindingResults, Model model) {
			if(!bindingResults.hasErrors()) {
				Piatto oldPiatto = piattoService.findById(id);
				oldPiatto.setId(piatto.getId());
				oldPiatto.setNome(piatto.getNome());
				oldPiatto.setDescrizione(piatto.getDescrizione());
				piattoService.updatePiatto(oldPiatto);
				model.addAttribute("piatto", oldPiatto);
				return "redirect:/admin/indexAdmin";
			}
			else
				return "admin/piatto/editPiatto.html";
		}
	 

}
