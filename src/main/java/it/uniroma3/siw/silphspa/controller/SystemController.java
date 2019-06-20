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
import it.uniroma3.siw.silphspa.services.AlbumService;
import it.uniroma3.siw.silphspa.services.FotoService;
import it.uniroma3.siw.silphspa.services.RichiestaService;


@Controller
public class SystemController {
	
	@Autowired
	private FotoService fotoService;
	
	@Autowired
	private AlbumService albumService;
	
	@Autowired
	private RichiestaService richiestaService;
	
	//HOME
	@RequestMapping(value = "/")
	public String home(Model model) {
		
		List<Foto> fotos = this.fotoService.MostraTutti();
		
		if(!fotos.isEmpty()) {
			Random random = new Random();
	        int index = random.nextInt(fotos.size());
	        index++;
			Foto f = this.fotoService.findById(index);
			model.addAttribute("foto", f);
			Fotografo fotografo = f.getFotografo();
			model.addAttribute("fotografo", fotografo);
			
			List<Foto> ultime8 = fotos.subList(fotos.size()-10, fotos.size());
			model.addAttribute("fotos", ultime8);
			
		}
		else {
			Foto f = new Foto();
			f.setIndirizzo("https://bit.ly/2XtMNSv");
			model.addAttribute("foto", f);
			model.addAttribute("fotogrfo", "Empty");
		}
		
		
		List<Album> albums = this.albumService.MostraTutti();
		if(!albums.isEmpty()) {
			Random random = new Random();
			int indexA = random.nextInt(albums.size());
		    indexA++;
		    Album a = this.albumService.AlbumPerId(indexA);
		    model.addAttribute("album", a);
		    List<Foto> fotoA = a.getFoto();
		    if(fotoA.isEmpty()) {
		        Foto def = new Foto();
		        def.setIndirizzo("https://bit.ly/2XtMNSv");
		        model.addAttribute("copertina", def);
		    }
		    else {
		        Foto fa = fotoA.get(0);
		        model.addAttribute("copertina", fa);
		    }
		}
       
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
		username = username.substring(0, 1).toUpperCase() + username.substring(1).toLowerCase();
		model.addAttribute("username", username);
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
	
	//RICHIESTE
	@RequestMapping(value="/vediRichieste")
	public String richieste(Model model) {
		model.addAttribute("richieste", this.richiestaService.MostraTutte());
		return "mostraRichieste.html";
	}
	
	
}
