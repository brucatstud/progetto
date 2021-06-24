package it.uniroma3.siw.personal.controller.validator;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.personal.model.Carne;
import it.uniroma3.siw.personal.model.Griglia;
import it.uniroma3.siw.personal.service.CarneService;
import it.uniroma3.siw.personal.service.GrigliaService;



@Component
public class GrigliaValidator implements Validator {
	@Autowired
	private GrigliaService grigliaService;
	
    private static final Logger logger = LoggerFactory.getLogger(GrigliaValidator.class);

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "modello", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "marca", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "capacita", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tempMassima", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descrizione", "required");


		if (!errors.hasErrors()) {
			logger.debug("confermato: valori non nulli");
			if (this.grigliaService.alreadyExists((Griglia)o)) {
				logger.debug("e' un duplicato");
				errors.reject("duplicato");
			}
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Griglia.class.equals(aClass);
	}
}
