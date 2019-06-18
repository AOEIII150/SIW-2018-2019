package it.uniroma3.siw.silphspa.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.silphspa.model.Carrello;
import it.uniroma3.siw.silphspa.model.Foto;
import it.uniroma3.siw.silphspa.services.FotoService;

@Controller
public class CarrelloController {
	
	@Autowired
	private FotoService fotoService;
	
	@RequestMapping(value="/fotoCarrello/{id}",method=RequestMethod.GET)
	public String fotoNelCarrello(@ModelAttribute("id") Long id, Model model, BindingResult bindingResult) {
		Carrello c = Carrello.getCarrello();
		Foto f = this.fotoService.findById(id);
		Set<Foto> fotos = c.getFotos();
		fotos.add(f);
		model.addAttribute("carrello", c);
		return "mostraCarrello.html";
	}
	
	@RequestMapping(value="/mostraCarrello")
	public String mostraCarrello(Model model) {
		model.addAttribute("carrello", Carrello.getCarrello());
		return "mostraCarrello.html";
	}
	
	

}
