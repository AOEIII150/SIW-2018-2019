package it.uniroma3.siw.silphspa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Carrello {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String urlImmagine;
	
	//GETTER E SETTER
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUrlImmagine() {
		return urlImmagine;
	}
	public void setUrlImmagine(String urlImmagine) {
		this.urlImmagine = urlImmagine;
	}
	
	

}
