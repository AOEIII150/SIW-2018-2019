package it.uniroma3.siw.silphspa.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.silphspa.model.Carrello;
import it.uniroma3.siw.silphspa.model.Foto;
import it.uniroma3.siw.silphspa.model.Richiesta;
import it.uniroma3.siw.silphspa.services.RichiestaService;
import it.uniroma3.siw.silphspa.services.RichiestaValidator;

@Controller
public class RichiestaController {
	
	@Autowired
	private RichiestaValidator richiestaValidator;
	
	@Autowired
	private RichiestaService richiestaService;
	
	@RequestMapping(value="/richiesta",method=RequestMethod.POST)
	public String nuovaRichiesta(@ModelAttribute("richiesta") Richiesta richiesta, Model model, BindingResult bindingResult) {
		
		this.richiestaValidator.validate(richiesta,bindingResult);
		if(!bindingResult.hasErrors()) {
			Set<Foto> fotos = Carrello.getCarrello().getFotos();
			richiesta.setFotos(fotos);
			this.richiestaService.inserisciRichiesta(richiesta);
			model.addAttribute("richiesta", richiesta);
			Carrello.getCarrello().deleteFotos(fotos);
			return "mostraRichiesta.html";
		}
		else {
			model.addAttribute("carrello", Carrello.getCarrello());
			return "richiestaForm.html";
		}
		
	}
	
	@RequestMapping(value="/inoltraRichiesta")
	public String inoltraRichiesta(Model model) {
		model.addAttribute("richiesta", new Richiesta());
		model.addAttribute("carrello", Carrello.getCarrello());
		return "richiestaForm.html";
	}
	
	@RequestMapping(value="/mostraRichiesta/{id}", method=RequestMethod.GET)
	public String richiesta(@PathVariable("id") Long id,Model model) {
		Richiesta r = this.richiestaService.findById(id);
		if(r == null) {
			return "/mostraRichieste";
		}
		else {
			model.addAttribute("richiesta", r);
			return "mostraRichiesta.html";
		}
	}
	
	

}
