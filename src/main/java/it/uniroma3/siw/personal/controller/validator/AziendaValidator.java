package it.uniroma3.siw.personal.controller.validator;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.personal.model.Azienda;
import it.uniroma3.siw.personal.model.Carne;
import it.uniroma3.siw.personal.service.AziendaService;
import it.uniroma3.siw.personal.service.CarneService;



@Component
public class AziendaValidator implements Validator {
	@Autowired
	private AziendaService aziendaService;
	
    private static final Logger logger = LoggerFactory.getLogger(AziendaValidator.class);

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "partitaIva", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telefono", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "indirizzoSede", "required");


		if (!errors.hasErrors()) {
			logger.debug("confermato: valori non nulli");
			if (this.aziendaService.alreadyExists((Azienda)o)) {
				logger.debug("e' un duplicato");
				errors.reject("duplicato");
			}
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Azienda.class.equals(aClass);
	}
}
