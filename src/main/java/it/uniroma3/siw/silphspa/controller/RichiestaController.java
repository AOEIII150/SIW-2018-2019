
/*
package it.uniroma3.siw.silphspa.controller;

	
	import javax.validation.Valid;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.validation.BindingResult;
	import org.springframework.web.bind.annotation.ModelAttribute;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestMethod;
	import it.uniroma3.siw.silphspa.model.Silph;
	import it.uniroma3.siw.silphspa.services.LoginValidator;
import it.uniroma3.siw.silphspa.services.RichiestaValidator;

	@Controller
	public class RichiestaController {

		@Autowired
		private RichiestaValidator richiestaValidator;

		@RequestMapping(value = "/richiesta",method=RequestMethod.POST)
		public String newSilph(@Valid @ModelAttribute("richiesta") Silph login, Model model, BindingResult bindingResult) {

			this.richiestaValidator.validate(richiesta, bindingResult);
			if(!bindingResult.hasErrors()) {
				return "pannelloDiControllo.html";
			}
			else {
				return "login.html";
			}
		}
		
		
		
		@RequestMapping(value = "/loginForm")
		public String loginForm(Model model) {
			model.addAttribute("silph" , new Silph());
			return "login.html";
		}
}

*/