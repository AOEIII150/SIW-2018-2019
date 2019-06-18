package it.uniroma3.siw.silphspa.controller;




import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;

import it.uniroma3.siw.silphspa.model.Foto;
import it.uniroma3.siw.silphspa.model.SilphStaff;
import it.uniroma3.siw.silphspa.services.FotoService;
import it.uniroma3.siw.silphspa.services.SilphStaffService;

@Controller
public class SystemController {
	
	@Autowired
	private SilphStaffService staffService;

	@Autowired
	private FotoService fotoService;
	
	//HOME
	@RequestMapping(value = "/")
	public String home(Model model) {
		List<Foto> fotos = this.fotoService.MostraTutti();
		Random random = new Random();
        int index = random.nextInt(fotos.size());
        index++;
		Foto f = this.fotoService.findById(index);
		model.addAttribute("foto", f);
		
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
	
	
	//PANNELLO DI CONTROLLO
	@RequestMapping(value="/pannelloDiControllo")
	public String pannelloDiControllo(Model model) {
		UserDetails staff = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = staff.getUsername();
		SilphStaff utente = this.staffService.findByUsername(username);
		model.addAttribute("utente", utente);
		return "pannelloDiControllo.html";
	}
	
	//CATEGORIE
	@RequestMapping(value = "/Categorie")
	public String categorie(Model model) {
		model.addAttribute("categorie");
		return "categorie.html";			//TODO
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
