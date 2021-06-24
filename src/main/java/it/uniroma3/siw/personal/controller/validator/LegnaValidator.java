package it.uniroma3.siw.personal.controller.validator;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.personal.model.Legna;
import it.uniroma3.siw.personal.service.CarneService;
import it.uniroma3.siw.personal.service.LegnaService;



@Component
public class LegnaValidator implements Validator {
	@Autowired
	private LegnaService legnaService;
	
    private static final Logger logger = LoggerFactory.getLogger(LegnaValidator.class);

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "provenienza", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "umidita", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "aroma", "required");


		if (!errors.hasErrors()) {
			logger.debug("confermato: valori non nulli");
			if (this.legnaService.alreadyExists((Legna)o)) {
				logger.debug("e' un duplicato");
				errors.reject("duplicato");
			}
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Legna.class.equals(aClass);
	}
}
