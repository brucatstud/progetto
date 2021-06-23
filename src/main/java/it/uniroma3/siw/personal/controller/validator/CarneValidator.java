package it.uniroma3.siw.personal.controller.validator;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.personal.model.Artista;
import it.uniroma3.siw.personal.model.Opera;
import it.uniroma3.siw.personal.service.ArtistaService;
import it.uniroma3.siw.personal.service.OperaService;



@Component
public class CarneValidator implements Validator {
	@Autowired
	private ArtistaService artistaService;
	
    private static final Logger logger = LoggerFactory.getLogger(CarneValidator.class);

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataDiNascita", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "luogoDiNascita", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nazionalita", "required");


		if (!errors.hasErrors()) {
			logger.debug("confermato: valori non nulli");
			if (this.artistaService.alreadyExists((Artista)o)) {
				logger.debug("e' un duplicato");
				errors.reject("duplicato");
			}
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Artista.class.equals(aClass);
	}
}
