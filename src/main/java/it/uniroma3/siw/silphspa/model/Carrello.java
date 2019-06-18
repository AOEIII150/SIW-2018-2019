package it.uniroma3.siw.silphspa.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

public final class Carrello {
	
	private static Carrello carrello;
	
	private List<Foto> fotos;
	
	private Carrello() {}
	
	@Bean
	@Scope("singleton")
	public static Carrello getCarrello() {
		if(carrello == null) {
			carrello = new Carrello();
		}
		return carrello;
	}

	public List<Foto> getFotos() {
		if(fotos == null) {
			fotos = new ArrayList<>();
		}
		return fotos;
	}

	public void setFotos(List<Foto> fotos) {
		this.fotos = fotos;
	}

}
