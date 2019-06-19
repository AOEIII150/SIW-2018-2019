package it.uniroma3.siw.silphspa.model;


import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

public final class Carrello {
	
	private static Carrello carrello;
	
	private Set<Foto> fotos;
	
	private Carrello() {
		this.fotos = new HashSet<>();
	}
	
	@Bean
	@Scope("singleton")
	public static Carrello getCarrello() {
		if(carrello == null) {
			carrello = new Carrello();
		}
		return carrello;
	}

	public Set<Foto> getFotos() {
		return this.fotos;
	}

	public void setFotos(Set<Foto> fotos) {
		this.fotos = fotos;
	}
	
	public void deleteFotos(Set<Foto> fotos) {
		this.fotos.removeAll(fotos);
	}

	
}
