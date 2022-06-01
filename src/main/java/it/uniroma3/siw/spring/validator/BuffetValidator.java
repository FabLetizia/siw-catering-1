package it.uniroma3.siw.spring.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import it.uniroma3.siw.spring.model.Buffet;
import it.uniroma3.siw.spring.service.BuffetService;

@Component
public class BuffetValidator implements Validator {
	@Autowired
	private BuffetService buffetService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Buffet.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		if (this.buffetService.alreadyExists((Buffet)o)) {
			errors.reject("buffet.duplicato");
			}
//		if(this.buffetService.listaPiattiVuota((Buffet)o)) {
//			errors.reject("buffet.senzaPiatti");
//		}
	}
}