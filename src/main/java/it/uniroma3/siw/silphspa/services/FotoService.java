package it.uniroma3.siw.silphspa.services;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.silphspa.model.Foto;
import it.uniroma3.siw.silphspa.repository.FotoRepository;

@Service
public class FotoService {
	
	@Autowired
	private FotoRepository fotoRepository;
	
	@Transactional
	public Foto inserisciFoto(Foto foto) {
			return fotoRepository.save(foto);
		
	}
	
	@Transactional
	public List<Foto> MostraTutti(){
		    return (List<Foto>)fotoRepository.findAll();
	}
	
}
