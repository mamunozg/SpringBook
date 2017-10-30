package com.marco.spittr.web;

import java.io.File;
import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

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
	public String processRegistration(@Valid @ModelAttribute("spitter") SpitterForm spitterForm, Errors errors, Model model) throws IllegalStateException, IOException {
		
		if(errors.hasErrors()) {
			return "registerForm";
		}
		Spitter spitter = spitterForm.toSpitter();
		repository.save(spitter);
		
		
		MultipartFile mpFile = spitterForm.getProfilePicture();
//		mpFile.transferTo(new File("/tmp/spittr/" + spitter.getUserName() + ".jpg"));
		mpFile.transferTo(new File(spitter.getUserName() + ".jpg"));
		model.addAttribute("userName", spitter.getUserName());
		return "redirect:/spitter/{userName}";
	}
	
	
	@RequestMapping(value="/{userName}", method=RequestMethod.GET)
	public String showSpitter(@PathVariable String userName, Model model) {
		
		if(!model.containsAttribute("spitter")) {
			model.addAttribute("spitter", repository.findByUserName(userName));
		}
						
		return "profile";
		
	}
	
	

}
