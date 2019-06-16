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

	public Fotografo fotografoPerId(Long id) {
		try {return fotografoRepository.findById(id).get();}
		catch(NoSuchElementException e) {return null;}
	}
	
}
