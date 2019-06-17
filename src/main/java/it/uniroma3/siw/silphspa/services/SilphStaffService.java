package it.uniroma3.siw.silphspa.services;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.silphspa.model.SilphStaff;
import it.uniroma3.siw.silphspa.repository.SilphRepository;

@Service
public class SilphStaffService {

	@Autowired
	private SilphRepository silphRepository;
	
	public SilphStaff findByUsername(String username) {
		try {return silphRepository.findByUsername(username);}
		catch(NoSuchElementException e) {return null;}
	}

}
