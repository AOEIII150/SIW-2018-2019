package it.uniroma3.siw.silphspa.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.silphspa.model.*;

@Repository
public interface StudenteRepository extends CrudRepository<Fotografo, Long>{
	//findBy
	public List<Fotografo> findByNome(String nome);
	public List<Fotografo> findByNomeAndCognome(String nome, String cognome);
	public List<Fotografo> findByMatricola(Long matricola);
	
}
