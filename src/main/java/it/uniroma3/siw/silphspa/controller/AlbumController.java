package it.uniroma3.siw.silphspa.controller;

	
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import it.uniroma3.siw.silphspa.model.Album;
import it.uniroma3.siw.silphspa.model.AlbumForm;
import it.uniroma3.siw.silphspa.model.Foto;
import it.uniroma3.siw.silphspa.model.Fotografo;
import it.uniroma3.siw.silphspa.model.SilphStaff;
import it.uniroma3.siw.silphspa.model.StringRicerca;
import it.uniroma3.siw.silphspa.services.AlbumService;
import it.uniroma3.siw.silphspa.services.AlbumFormValidator;
import it.uniroma3.siw.silphspa.services.FotografoService;
import it.uniroma3.siw.silphspa.services.SilphStaffService;


	@Controller
	public class AlbumController {

		@Autowired
		private AlbumFormValidator albumValidator;
		
		@Autowired
		private AlbumService albumService;
		
		@Autowired
		private FotografoService fotografoService;
		
		@Autowired
		private SilphStaffService staffService;
		

		@RequestMapping(value = "/album",method=RequestMethod.POST)
		public String newAlbum(@Valid @ModelAttribute("albumForm") AlbumForm albumForm, Model model, BindingResult bindingResult) {

			this.albumValidator.validate(albumForm, bindingResult);
			if(!bindingResult.hasErrors()) {
				Fotografo fotografo = this.fotografoService.fotografoPerId(albumForm.getIdFotografo());
				if(fotografo != null) {
					Album album = new Album();
					String nome = albumForm.getNome().substring(0, 1).toUpperCase() + albumForm.getNome().substring(1).toLowerCase();				
					album.setTitolo(nome);
					album.setFotografo(fotografo);
					List<Album> albums = fotografo.getAlbum();
					albums.add(album);
					fotografo.setAlbum(albums);
					this.albumService.inserisciAlbum(album);
					return "confermaInserimentoAlbum.html";
				}
				else {
					bindingResult.rejectValue("idFotografo", "wrong");
					return "albumForm.html";
				}
			}
			else {
				return "albumForm.html";
			}
		}
		
		@RequestMapping(value="/mostraAlbums", method=RequestMethod.GET)
		public String mostraAlbums(Model model) {
			List<Album> albums = this.albumService.MostraTutti();
			model.addAttribute("albums",albums);
			return "mostraAlbums.html";
		}
		
		@RequestMapping(value="/cercaAlbum", method=RequestMethod.POST)
		public String trovaAlbum(@ModelAttribute("stringRicerca") StringRicerca stringRicerca, Model model, BindingResult bindingResult) {
			
			if(stringRicerca.getS1().equals("") && stringRicerca.getS2().equals("")) {
				bindingResult.rejectValue("s3", "wrong");
				return "ricercaAlbum.html";
			}
			else {
				if(!stringRicerca.getS1().equals("") && stringRicerca.getS2().equals("")) {
					String nome = stringRicerca.getS1().substring(0, 1).toUpperCase() + stringRicerca.getS1().substring(1).toLowerCase();
					List<Album> a = this.albumService.findByNome(nome);
					model.addAttribute("albums", a);
					model.addAttribute("utente","ADMIN");
					return "mostraAlbums.html";
				}
				else {
						Long id = Long.valueOf(stringRicerca.getS2()).longValue();
						Album a = this.albumService.AlbumPerId(id);
						if(a!=null) {
							model.addAttribute("utente", "ADMIN");
							model.addAttribute("album", a);
							List<Foto> f = a.getFoto();
							if(!f.isEmpty()) {
								model.addAttribute("fotos", f);
							}
							return "mostraAlbum.html";
						}
						else {
							bindingResult.rejectValue("s3", "checkId");
							return "ricercaAlbum.html";
						}
				}
			}
			
		}
		
		@RequestMapping(value="/cercaAlbum")
		public String cercaAlbum(Model model) {
			model.addAttribute("stringRicerca", new StringRicerca());
			return "ricercaAlbum.html";
		}
		
		
		@RequestMapping(value = "/addAlbum")
		public String albumForm(Model model) {
			model.addAttribute("albumForm" , new AlbumForm());
			return "albumForm.html";
		}
		
		@RequestMapping(value="/mostraAlbum/{id}", method=RequestMethod.GET)
		public String Album(@PathVariable("id") Long id,Model model) {
			
			Album a = this.albumService.AlbumPerId(id);
			if(a == null) {
				return "/mostraAlbums";
			}
			else {
				model.addAttribute("album", a);
				List<Foto> f = a.getFoto();
				if(!f.isEmpty()) {
					model.addAttribute("fotos", f);
				}
				return "mostraAlbum.html";
			}
		}
		
		@RequestMapping(value="/mostraAlbumAdmin/{id}", method=RequestMethod.GET)
		public String AlbumAdmin(@PathVariable("id") Long id,Model model) {
			
			model.addAttribute("utente", "ADMIN");
			
			Album a = this.albumService.AlbumPerId(id);
			if(a == null) {
				return "/mostraAlbums";
			}
			else {
				model.addAttribute("album", a);
				List<Foto> f = a.getFoto();
				if(!f.isEmpty()) {
					model.addAttribute("fotos", f);
				}
				return "mostraAlbum.html";
			}
		}
}