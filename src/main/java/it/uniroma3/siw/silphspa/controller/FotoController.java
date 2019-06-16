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
import it.uniroma3.siw.silphspa.model.Foto;
import it.uniroma3.siw.silphspa.model.FotoForm;
import it.uniroma3.siw.silphspa.model.Fotografo;
import it.uniroma3.siw.silphspa.services.AlbumService;
import it.uniroma3.siw.silphspa.services.FotoFormValidator;
import it.uniroma3.siw.silphspa.services.FotoService;
import it.uniroma3.siw.silphspa.services.FotografoService;

@Controller
public class FotoController {

	@Autowired
	private FotoFormValidator fotoFormValidator;

	@Autowired
	private AlbumService albumService;

	@Autowired
	private FotografoService fotografoService;

	@Autowired
	private FotoService fotoService;

	@RequestMapping(value = "/foto",method=RequestMethod.POST)
	public String newFoto(@Valid @ModelAttribute("fotoForm") FotoForm fotoForm, Model model, BindingResult bindingResult) {

		this.fotoFormValidator.validate(fotoForm, bindingResult);
		if(!bindingResult.hasErrors()) {
			Fotografo fotografo = this.fotografoService.fotografoPerId(fotoForm.getIdFotografo());
			Album album = this.albumService.AlbumPerId(fotoForm.getIdAlbum());
			if(fotografo != null) {
				Foto foto = new Foto();
				String nome = fotoForm.getNome().substring(0, 1).toUpperCase() + fotoForm.getNome().substring(1).toLowerCase();		
				foto.setTitolo(nome);
				foto.setFotografo(fotografo);
					if(album != null) {
						foto.setIndirizzo(fotoForm.getIndirizzo());
						foto.setAlbum(album);
						List<Foto> fotos = fotografo.getFoto();
						fotos.add(foto);
						fotografo.setFoto(fotos);
						this.fotoService.inserisciFoto(foto);
						List<Foto> fotoa = album.getFoto();
						fotoa.add(foto);
						album.setFoto(fotoa);
						this.fotoService.inserisciFoto(foto);
						return "confermaInserimentoFoto.html"; //SUCCESSO
					}
					else {
						bindingResult.rejectValue("idAlbum", "wrong");
						return "fotoForm.html";
					}
			}
			else {
				bindingResult.rejectValue("idFotografo", "wrong");
					return "fotoForm.html";
				}
			}
		return "fotoForm.html";

		}


	@RequestMapping(value = "/fotoForm")
	public String fotoForm(Model model) {
		model.addAttribute("fotoForm" , new FotoForm());
		return "fotoForm.html";
	}
}
