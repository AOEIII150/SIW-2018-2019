package it.uniroma3.siw.silphspa.model;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
public class Fotografo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String cognome;
	private String imgProfilo;
	
	@OneToMany (mappedBy = "fotografo")
	private List<Foto> foto;
	
	@OneToMany (mappedBy = "fotografo")
	private List<Album> album;

	
	//GETTER E SETTER
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public List<Album> getAlbum() {
		return album;
	}
	public void setAlbum(List<Album> album) {
		this.album = album;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Foto> getFoto() {
		return foto;
	}
	public void setFoto(List<Foto> foto) {
		this.foto = foto;
	}
	public String getImgProfilo() {
		return imgProfilo;
	}
	public void setImgProfilo(String imgProfilo) {
		this.imgProfilo = imgProfilo;
	}
	
	
}
