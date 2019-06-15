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
	private String tag;
	
	@ManyToOne
	private Album album;
	
	@ManyToOne
	private Fotografo fotografo;
	
	//GETTER E SETTER
	public String getTitolo() {
		return titolo;
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
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}

}
