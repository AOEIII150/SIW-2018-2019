package it.uniroma3.siw.silphspa.services;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.silphspa.model.Studente;

@Component
public class StudenteValidator implements Validator{

	@Override
	public boolean supports(Class<?> aClazz) {
		return Studente.class.equals(aClazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"nome","required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"cognome","required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"matricola" , "requred");
		
	}

}
