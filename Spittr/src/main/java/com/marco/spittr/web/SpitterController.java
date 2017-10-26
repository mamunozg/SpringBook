package com.marco.spittr.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
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
	public String processRegistration(@Valid Spitter spitter, Errors error) {
		
		if(error.hasErrors()) {
			return "registerForm";
		}
		
		repository.save(spitter);
		
		return "redirect:/spitter/" + spitter.getUserName();
	}
	
	
	@RequestMapping(value="/{userName}", method=RequestMethod.GET)
	public String showSpitter(@PathVariable String userName, Model model) {		
		model.addAttribute("spitter", repository.findByUserName(userName));		
		return "profile";
		
	}

}
