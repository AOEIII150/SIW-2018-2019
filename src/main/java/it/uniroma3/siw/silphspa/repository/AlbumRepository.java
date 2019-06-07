package it.uniroma3.siw.silphspa.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import it.uniroma3.siw.silphspa.model.Album;

public interface AlbumRepository extends CrudRepository<Album , Long> {
	//findBy Titolo Album
	public List<Album> findByTitolo(String titolo);
	
}
