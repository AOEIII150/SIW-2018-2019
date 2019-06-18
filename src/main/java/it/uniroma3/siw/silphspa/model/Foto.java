package it.uniroma3.siw.silphspa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Foto {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO )
	private Long id;
	private String titolo;
	private String indirizzo;
	
	@ManyToOne
	private Album album;
	
	@ManyToOne
	private Fotografo fotografo;
	
	//GETTER E SETTER
	public String getTitolo() {
		return titolo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Fotografo getFotografo() {
		return fotografo;
	}
	public void setFotografo(Fotografo fotografo) {
		this.fotografo = fotografo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	
	public Album getAlbum() {
		return album;
	}
	public void setAlbum(Album album) {
		this.album = album;
	}
	@Override
	public int hashCode() {
		return this.id.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		Foto foto = (Foto) obj;
		return this.id.equals(foto.getId());
	}
	
	

}
