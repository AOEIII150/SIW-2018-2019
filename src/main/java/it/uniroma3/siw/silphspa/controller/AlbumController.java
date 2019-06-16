package it.uniroma3.siw.silphspa.controller;

	
	import java.util.List;

import javax.validation.Valid;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.validation.BindingResult;
	import org.springframework.web.bind.annotation.ModelAttribute;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.silphspa.model.Album;
import it.uniroma3.siw.silphspa.model.AlbumForm;
import it.uniroma3.siw.silphspa.model.Fotografo;
import it.uniroma3.siw.silphspa.services.AlbumService;
import it.uniroma3.siw.silphspa.services.AlbumFormValidator;
import it.uniroma3.siw.silphspa.services.FotografoService;

	@Controller
	public class AlbumController {

		@Autowired
		private AlbumFormValidator albumValidator;
		
		@Autowired
		private AlbumService albumService;
		
		@Autowired
		private FotografoService fotografoService;

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
		
		
		
		@RequestMapping(value = "/albumForm")
		public String albumForm(Model model) {
			model.addAttribute("albumForm" , new AlbumForm());
			return "albumForm.html";
		}
}