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
import it.uniroma3.siw.personal.model.Pitmaster;
import it.uniroma3.siw.personal.service.CarneService;
import it.uniroma3.siw.personal.service.GrigliaService;
import it.uniroma3.siw.personal.service.PitmasterService;



@Component
public class PitmasterValidator implements Validator {
	@Autowired
	private PitmasterService pitmasterService;
	
    private static final Logger logger = LoggerFactory.getLogger(PitmasterValidator.class);

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "matricola", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numeroDiTelefono", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nazionalita", "required");


		if (!errors.hasErrors()) {
			logger.debug("confermato: valori non nulli");
			if (this.pitmasterService.alreadyExists((Pitmaster)o)) {
				logger.debug("e' un duplicato");
				errors.reject("duplicato");
			}
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Pitmaster.class.equals(aClass);
	}
}
