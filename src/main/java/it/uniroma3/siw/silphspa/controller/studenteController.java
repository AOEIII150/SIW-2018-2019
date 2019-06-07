package it.uniroma3.siw.silphspa.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.silphspa.model.Studente;
import it.uniroma3.siw.silphspa.services.StudenteService;
import it.uniroma3.siw.silphspa.services.StudenteValidator;


@Controller
public class studenteController {


	@Autowired
	private StudenteService studenteService;

	@Autowired
	private StudenteValidator studenteValidator;

	@RequestMapping(value = "/studente",method=RequestMethod.POST)
	public String newStudente(@Valid @ModelAttribute("studente") Studente studente, Model model, BindingResult bindingResult) {

		this.studenteValidator.validate(studente,bindingResult);
		if(!bindingResult.hasErrors()) {
			this.studenteService.inserisciStudente(studente);
			model.addAttribute("studenti",this.studenteService.MostraTutti());
			return "studenti.html";
		}
		else {
			return "studenteForm.html";
		}
	}

	@RequestMapping(value = "/studente/{id}", method=RequestMethod.GET)
	public String getStudente(@PathVariable("id") Long id, Model model) {
		if(id!=null) {
			model.addAttribute("studente",studenteService.studentePerId(id));
			return "studente.html";
		}else {
			return "studenti.html";
		}
	}
	
	@RequestMapping(value ="/addStudente")
	public String addStudente(Model model) {
		model.addAttribute("studente",new Studente());
		return "studenteForm.html";
	}
}
