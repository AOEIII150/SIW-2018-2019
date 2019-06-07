package it.uniroma3.siw.silphspa.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.silphspa.model.Foto;

public interface FotoRepository extends CrudRepository <Foto , Long> {
	
	//finfBy Tag associata alla foto
	public List<Foto> findByTag(String tag);

}
