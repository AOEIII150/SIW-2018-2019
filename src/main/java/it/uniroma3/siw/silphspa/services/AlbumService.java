package it.uniroma3.siw.silphspa.services;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.silphspa.model.Album;
import it.uniroma3.siw.silphspa.repository.AlbumRepository;

@Service
public class AlbumService {
	@Autowired
	private AlbumRepository albumRepository;
	
	@Transactional
	public Album inserisciAlbum(Album album) {
			return albumRepository.save(album);
		
	}
	
	@Transactional
	public List<Album> MostraTutti(){
		    return (List<Album>)albumRepository.findAll();
	}
	
}
