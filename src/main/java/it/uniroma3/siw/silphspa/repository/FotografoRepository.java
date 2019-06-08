package it.uniroma3.siw.silphspa.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.silphspa.model.*;

@Repository
public interface FotografoRepository extends CrudRepository<Fotografo, Long>{
	
	//findBy Cognome e matricola fotografo
	public List<Fotografo> findByCognome(String cognome);
	public List<Fotografo> findByMatricola(Long matricola);
	
}
