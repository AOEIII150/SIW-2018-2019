package it.uniroma3.siw.silphspa.controller;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.silphspa.model.Album;
import it.uniroma3.siw.silphspa.model.Foto;
import it.uniroma3.siw.silphspa.model.Fotografo;
import it.uniroma3.siw.silphspa.model.StringRicerca;
import it.uniroma3.siw.silphspa.services.FotografoService;
import it.uniroma3.siw.silphspa.services.FotografoValidator;


@Controller
public class FotografoController {


	@Autowired
	private FotografoService fotografoService;

	@Autowired
	private FotografoValidator fotografoValidator;

	@RequestMapping(value = "/fotografo",method=RequestMethod.POST)
	public String newFotografo(@Valid @ModelAttribute("fotografo") Fotografo fotografo, Model model, BindingResult bindingResult) {

		this.fotografoValidator.validate(fotografo,bindingResult);
		if(!bindingResult.hasErrors()) {
			String nome = fotografo.getNome().substring(0, 1).toUpperCase() + fotografo.getNome().substring(1).toLowerCase();
			String cognome = fotografo.getCognome().substring(0, 1).toUpperCase() + fotografo.getCognome().substring(1).toLowerCase();
			fotografo.setNome(nome);
			fotografo.setCognome(cognome);
			this.fotografoService.inserisciFotografo(fotografo);
			return "confermaInserimentoFotografo.html";
		}
		else {
			return "fotografoForm.html";
		}
	}
	
	@RequestMapping(value="/mostraFotografi", method=RequestMethod.GET)
	public String mostraFotografi(Model model) {
		List<Fotografo> fotografi = this.fotografoService.MostraTutti();
		model.addAttribute("fotografi",fotografi);
		return "mostraFotografi.html";
	}
	
	@RequestMapping(value="/cercaFotografo", method=RequestMethod.POST)
	public String trovaFotografo(@ModelAttribute("stringRicerca") StringRicerca stringRicerca, Model model, BindingResult bindingResult) {
		
		
		if(!stringRicerca.getS1().equals("") && !stringRicerca.getS2().equals("") && stringRicerca.getS3().equals("")) {			
			String nome = stringRicerca.getS1().substring(0, 1).toUpperCase() + stringRicerca.getS1().substring(1).toLowerCase();
			String cognome = stringRicerca.getS2().substring(0, 1).toUpperCase() + stringRicerca.getS2().substring(1).toLowerCase();
			List<Fotografo> f = this.fotografoService.findByNomeAndCognome(nome,cognome);
			if(f.isEmpty()) {
				bindingResult.rejectValue("s3", "notFound");
				return "ricercaFotografo.html";
			}
			else {
				model.addAttribute("fotografi", f);
				model.addAttribute("utente","ADMIN");
				model.addAttribute("stringRicerca", stringRicerca);
				return "mostraFotografi.html";
			}
		}
		else {
			if(!stringRicerca.getS1().equals("") && stringRicerca.getS2().equals("") && stringRicerca.getS3().equals("")) {
				String nome = stringRicerca.getS1().substring(0, 1).toUpperCase() + stringRicerca.getS1().substring(1).toLowerCase();
				List<Fotografo> f = this.fotografoService.findByNome(nome);
				if(f.isEmpty()) {
					bindingResult.rejectValue("s3", "notFound");
					return "ricercaFotografo.html";
				}
				else {
					model.addAttribute("fotografi", f);
					model.addAttribute("utente","ADMIN");
					model.addAttribute("stringRicerca", stringRicerca);
					return "mostraFotografi.html";
				}
			}
			else {
				if(stringRicerca.getS1().equals("") && !stringRicerca.getS2().equals("") && stringRicerca.getS3().equals("")) {
					String cognome = stringRicerca.getS2().substring(0, 1).toUpperCase() + stringRicerca.getS2().substring(1).toLowerCase();
					List<Fotografo> f = this.fotografoService.findByCognome(cognome);
					if(f.isEmpty()) {
						bindingResult.rejectValue("s3", "notFound");
						return "ricercaFotografo.html";
					}
					else {
						model.addAttribute("fotografi", f);
						model.addAttribute("utente","ADMIN");
						model.addAttribute("stringRicerca", stringRicerca);
						return "mostraFotografi.html";
					}
				}
				else {
					if((stringRicerca.getS1().equals("") && stringRicerca.getS2().equals("") && !stringRicerca.getS3().equals("")) ||
							(!stringRicerca.getS1().equals("") && !stringRicerca.getS2().equals("") && !stringRicerca.getS3().equals(""))) {
						Long id = Long.valueOf(stringRicerca.getS3()).longValue();
						Fotografo f = this.fotografoService.fotografoPerId(id);
						if(f!=null) {
							model.addAttribute("utente", "ADMIN");
							model.addAttribute("fotografo", f);
							return "mostraFotografo.html";
						}
						else {
							bindingResult.rejectValue("s3", "checkId");
							return "ricercaFotografo.html";
						}
					}
					else {
						bindingResult.rejectValue("s3", "wrong");
						return "ricercaFotografo.html";
					}
				}
			}
		}
		
	}

	@RequestMapping(value="/cercaFotografo")
	public String cercaFotografo(Model model) {
		model.addAttribute("stringRicerca", new StringRicerca());
		return "ricercaFotografo.html";
	}
	
	@RequestMapping(value="/cercaFotografoIndietro")
	public String cercafotografoIndietro() {
		return "ricercaFotografo.html";
	}
	
	@RequestMapping(value ="/addFotografo")
	public String addFotografo(Model model) {
		model.addAttribute("fotografo",new Fotografo());
		return "fotografoForm.html";
	}
	
	@RequestMapping(value="/fotografo/{id}", method=RequestMethod.GET)
	public String fotografo(@PathVariable("id") Long id,Model model) {
		Fotografo f = this.fotografoService.fotografoPerId(id);		
		if(f == null) {
			return "/mostraFotografi";
		}
		else {
			model.addAttribute("fotografo", f);
			return "mostraFotografo.html";
		}
	}
	
	@RequestMapping(value="/fotografoAdmin/{id}", method=RequestMethod.GET)
	public String fotografoAdmin(@PathVariable("id") Long id,Model model) {
		
		model.addAttribute("utente", "ADMIN");
		
		Fotografo f = this.fotografoService.fotografoPerId(id);
		if(f == null) {
			return "/mostraFotografi";
		}
		else {
			model.addAttribute("fotografo", f);
			return "mostraFotografo.html";
		}
	}
	
	@RequestMapping(value="/mostraAlbumFotografo/{id}", method=RequestMethod.GET)
	public String fotografoAlbums(@PathVariable("id") Long id, Model model) {
		Fotografo f = this.fotografoService.fotografoPerId(id);
		List<Album> albums = f.getAlbum();
		model.addAttribute("albums", albums);
		model.addAttribute("fotografo", f);
		model.addAttribute("f", "EXIST");
		return "mostraAlbums.html";
	}
	
	@RequestMapping(value="/mostraFotoFotografo/{id}", method=RequestMethod.GET)
	public String fotografoFotos(@PathVariable("id") Long id, Model model) {
		Fotografo f = this.fotografoService.fotografoPerId(id);
		List<Foto> fotos = f.getFoto();
		model.addAttribute("fotos", fotos);
		model.addAttribute("fotografo", f);
		model.addAttribute("f", "EXIST");
		return "mostraFotos.html";
	}
}
