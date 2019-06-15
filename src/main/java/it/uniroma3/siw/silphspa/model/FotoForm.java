package it.uniroma3.siw.silphspa.model;

public class FotoForm {
	
	private String nome;
	private Long idFotografo;
	private Long idAlbum;
	private String indirizzo;
	
	
	//GETTER E SETTER
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getIdFotografo() {
		return idFotografo;
	}
	public void setIdFotografo(Long idFotografo) {
		this.idFotografo = idFotografo;
	}
	public Long getIdAlbum() {
		return idAlbum;
	}
	public void setIdAlbum(Long idAlbum) {
		this.idAlbum = idAlbum;
	}

}
