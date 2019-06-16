package it.uniroma3.siw.silphspa.services;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.silphspa.model.Fotografo;
import it.uniroma3.siw.silphspa.repository.FotografoRepository;

@Service
public class FotografoService {
	@Autowired
	private FotografoRepository fotografoRepository;
	
	@Transactional
	public Fotografo inserisciFotografo(Fotografo fotografo) {
			return fotografoRepository.save(fotografo);
		
	}
	
	@Transactional
	public List<Fotografo> MostraTutti(){
		    return (List<Fotografo>)fotografoRepository.findAll();
	}

	@Transactional
	public Fotografo fotografoPerId(Long id) {
		try {return fotografoRepository.findById(id).get();}
		catch(NoSuchElementException e) {return null;}
	}
	
	@Transactional
	public List<Fotografo> findByNomeAndCognome(String nome, String cognome){
		try {return fotografoRepository.findByNomeAndCognome(nome, cognome);}
		catch(NoSuchElementException e) { return null;}
	}
	
	@Transactional
	public List<Fotografo> findByNome(String nome){
		try {return fotografoRepository.findByNome(nome);}
		catch(NoSuchElementException e) { return null;}
	}
	
	@Transactional
	public List<Fotografo> findByCognome(String cognome){
		try {return fotografoRepository.findByCognome(cognome);}
		catch(NoSuchElementException e) { return null;}
	}
	
}
