package it.uniroma3.siw.silphspa.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Album {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String titolo;
	
	//private Foto copertina;
	
	
	@OneToMany(mappedBy = "album")
	private List<Foto> foto;
	
	@ManyToOne
	private Fotografo fotografo;
	
	//GETTER E SETTER
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Foto> getFoto() {
		return this.foto;
	}
	public void setFoto(List<Foto> foto) {
		this.foto = foto;
	}
	public Fotografo getFotografo() {
		return fotografo;
	}
	public void setFotografo(Fotografo fotografo) {
		this.fotografo = fotografo;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	/*public Foto getCopertina() {
		return copertina;
	}
	public void setCopertina(Foto copertina) {
		this.copertina = copertina;
	}*/

}
