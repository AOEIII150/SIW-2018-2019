package it.uniroma3.siw.silphspa.repository;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import it.uniroma3.siw.silphspa.model.Richiesta;

@Repository
public interface RichiestaRepository extends CrudRepository<Richiesta, Long>{

}
