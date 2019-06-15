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

	@Controller
	public class richiestaForm {

		@Autowired
		private LoginValidator loginValidator;

		@RequestMapping(value = "/login",method=RequestMethod.POST)
		public String newSilph(@Valid @ModelAttribute("silph") Silph login, Model model, BindingResult bindingResult) {

			this.loginValidator.validate(login, bindingResult);
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