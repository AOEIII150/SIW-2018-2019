package it.uniroma3.siw.silphspa.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.silphspa.model.*;

@Repository
public interface StudenteRepository extends CrudRepository<Studente, Long>{
	//findBy
	public List<Studente> findByNome(String nome);
	public List<Studente> findByNomeAndCognome(String nome, String cognome);
	public List<Studente> findByMatricola(Long matricola);
	
}
