package it.uniroma3.siw.silphspa.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.silphspa.model.Fotografo;
import it.uniroma3.siw.silphspa.repository.StudenteRepository;

@Service
public class StudenteService {
	@Autowired
	private StudenteRepository studenteRepository;
	
	@Transactional
	public Fotografo inserisciStudente(Fotografo studente) {
			return studenteRepository.save(studente);
		
	}
	
	@Transactional
	public List<Fotografo> MostraTutti(){
		    return (List<Fotografo>)studenteRepository.findAll();
	}

	public Object studentePerId(Long id) {
		return studenteRepository.findById(id).get();
	}
	
}
