package it.uniroma3.siw.spring.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Piatto;
import it.uniroma3.siw.spring.service.PiattoService;

@Component
public class PiattoValidator implements Validator{
	
	@Autowired PiattoService piattoService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Piatto.class.equals(clazz);

	}

	@Override
	public void validate(Object o, Errors errors) {
		if(this.piattoService.alredyExists((Piatto) o)) {
			errors.reject("piatto.duplicato");
		}
		Piatto p = (Piatto)o;
		if(p.getIngredienti() == null || p.getIngredienti().size() < 2) {
			errors.reject("piatto.vuoto");
		}
	}

}
