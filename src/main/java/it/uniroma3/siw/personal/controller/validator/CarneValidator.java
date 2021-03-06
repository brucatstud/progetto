package it.uniroma3.siw.personal.controller.validator;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.personal.model.Carne;
import it.uniroma3.siw.personal.service.CarneService;



@Component
public class CarneValidator implements Validator {
	@Autowired
	private CarneService carneService;
	
    private static final Logger logger = LoggerFactory.getLogger(CarneValidator.class);

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "provenienza", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataDiMacello", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "eta", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "periodoFrollatura", "required");


		if (!errors.hasErrors()) {
			logger.debug("confermato: valori non nulli");
			if (this.carneService.alreadyExists((Carne)o)) {
				logger.debug("e' un duplicato");
				errors.reject("duplicato");
			}
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Carne.class.equals(aClass);
	}
}
