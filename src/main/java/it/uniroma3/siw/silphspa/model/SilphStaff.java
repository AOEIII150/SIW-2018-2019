package it.uniroma3.siw.silphspa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SilphStaff {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "serial", nullable = false)
	private Long id;
	//Dati di login per lo staff SILPH
	
	@Column(name = "username", unique=true)
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "role")
	private String role;
	
	
	/**
     * Constructor
     *
     * @param id The id of this User
     * @param username The username of this User for authentication
     * @param password The password of this User for authentication
     * @param role The authorization role for this User
     */
    public SilphStaff(String username, String password, String role) {
		super();
		this.password = password;
		this.username = username;
		this.role = role;
	}
	
    public SilphStaff() {
    	
    }
	
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
	public String getRole() {
		return this.role;
	}
	public void SetRole(String role) {
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setRole(String role) {
		this.role = role;
	}



}
