package it.uniroma3.siw.silphspa.services;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.silphspa.model.Foto;
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
	
	@Transactional
	public List<Richiesta> MostraTutte(){
		    return (List<Richiesta>)richiestaRepository.findAll();
	}

	
	public Richiesta findById(long id) {
		try {return richiestaRepository.findById(id).get();}
		catch(NoSuchElementException e) {return null;}
	}
	
}
