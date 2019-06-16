package it.uniroma3.siw.silphspa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SystemController {
	
	
	//HOME
	@RequestMapping(value = "/")
	public String home(Model model) {
		return "index.html";
	}
	
	//LOGIN
	@RequestMapping(value="/login")
	public String login() {
		return "login.html";
	}
	
	//LOGOUT
	@RequestMapping(value="/logout")
	public String logout() {
		return "logout.html";
	}
	
	
	//PannelloDiControllo
	@RequestMapping(value="/pannelloDiControllo")
	public String pannelloDiControllo() {
		return "pannelloDiControllo.html";
	}
	
	//CATEGORIE
	@RequestMapping(value = "/Categorie")
	public String categorie(Model model) {
		model.addAttribute("categorie");
		return "categorie.html";			//TODO
	}
	
	//FOTOS
	@RequestMapping(value = "/fotos")
	public String fotos(Model model) {
		model.addAttribute("fotos");
		return "fotos.html";		//TODO
	}
	
	//ALBUMS
	@RequestMapping(value = "/albums")
	public String albums(Model model) {
		model.addAttribute("albums");
		return "albums.html";			//TODO
	}
	
	//FOTOGRAFI
	@RequestMapping(value = "/fotografi")
	public String fotografi() {
		return "fotografi.html";		
	}
	
	//CARRELLO
	@RequestMapping(value = "/carrello")
	public String carrello(Model model) {
		model.addAttribute("carrello");
		return "carrello.html";
	}
	
	
	//ABOUT
	@RequestMapping(value = "/about")
	public String about(Model model) {
		model.addAttribute("about");
		return "about.html";
	}
	
	
}