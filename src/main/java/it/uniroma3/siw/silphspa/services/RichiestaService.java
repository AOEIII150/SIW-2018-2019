package it.uniroma3.siw.silphspa.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.silphspa.model.Richiesta;
import it.uniroma3.siw.silphspa.repository.RichiestaRepository;

@Service
public class RichiestaService {

	@Autowired
	private RichiestaRepository richiestaRepository;
	
	@Transactional
	public Richiesta inserisciRichiesta(Richiesta richiesta) {
			return richiestaRepository.save(richiesta);
		
	}
	
}
