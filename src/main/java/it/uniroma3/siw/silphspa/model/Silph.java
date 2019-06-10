package it.uniroma3.siw.silphspa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Silph {
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	//Dati di login per lo staff SILPH
	private String username;
	private String password;
	
	
	//GETTER E SETTER
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	


}
