package it.uniroma3.siw.silphspa.services;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import it.uniroma3.siw.silphspa.model.AlbumForm;

@Component
public class FotoValidator implements Validator{

	@Override
	public boolean supports(Class<?> aClazz) {
		return AlbumForm.class.equals(aClazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"titolo","required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"indirizzo","required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"tag","required");
		
	}

}
