package com.marco.spittr.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.marco.spittr.Spitter;
import com.marco.spittr.data.SpitterRepository;	

@Controller
@RequestMapping("/spitter")
public class SpitterController {

	
	private SpitterRepository repository;
		
	@Autowired
	public SpitterController(SpitterRepository repository) {
		this.repository = repository;
	}

	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String showRegistrationForm(Model model) {
		model.addAttribute(new Spitter());
		return "registerForm";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String processRegistration(@Valid Spitter spitter, BindingResult errors, Model m) {
		
		
		System.out.println(errors.hasErrors());
		
		
		if(errors.hasErrors()) {
			return "registerForm";
		}		
		repository.save(spitter);
		
	
//		return "redirect:/spitter/{userName}";
		return "redirect:/spitter/" + spitter.getUserName();
	}
	
	@RequestMapping(value="/me", method=RequestMethod.GET)
	public String me() {
		System.out.println("ME ME ME ME ME ME ME ME ME ME ME");
		return "home";
	}
	
	
	@RequestMapping(value="/{userName}", method=RequestMethod.GET)
	public String showSpitterProfile(@PathVariable String userName, Model model) {
		
//		if(!model.containsAttribute("spitter")) {
//			model.addAttribute("spitter", repository.findByUserName(userName));
//		}
//						
//		return "profile";
		
		Spitter spitter = repository.findByUserName(userName);
		model.addAttribute(spitter);
		
		return "profile";				
	}
	
	

}
