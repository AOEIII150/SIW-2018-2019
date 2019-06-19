package it.uniroma3.siw.silphspa.controller;




import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;

import it.uniroma3.siw.silphspa.model.Album;
import it.uniroma3.siw.silphspa.model.Foto;
import it.uniroma3.siw.silphspa.model.Fotografo;
import it.uniroma3.siw.silphspa.model.SilphStaff;
import it.uniroma3.siw.silphspa.services.AlbumService;
import it.uniroma3.siw.silphspa.services.FotoService;
import it.uniroma3.siw.silphspa.services.FotografoService;
import it.uniroma3.siw.silphspa.services.SilphStaffService;

@Controller
public class SystemController {
	
	@Autowired
	private SilphStaffService staffService;

	@Autowired
	private FotoService fotoService;
	
	@Autowired
	private AlbumService albumService;
	
	@Autowired
	private FotografoService fotografoService;
	
	//HOME
	@RequestMapping(value = "/")
	public String home(Model model) {
		List<Foto> fotos = this.fotoService.MostraTutti();
		Random random = new Random();
        int index = random.nextInt(fotos.size());
        index++;
        System.out.println("XXXXX = " +index);
		Foto f = this.fotoService.findById(index);
		model.addAttribute("foto", f);
		Fotografo fotografo = f.getFotografo();
		model.addAttribute("fotografo", fotografo);
		
		List<Album> albums = this.albumService.MostraTutti();
        int indexA = random.nextInt(albums.size());
        indexA++;
        Album a = this.albumService.AlbumPerId(indexA);
        model.addAttribute("album", a);
        
        List<Foto> fotoA = a.getFoto();
        Foto fa = fotoA.get(0);
        model.addAttribute("copertina", fa);
        
        
        model.addAttribute("fotos", fotos);
        model.addAttribute("fotografi", this.fotografoService.MostraTutti());
   
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
