package com.marco.spittr.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.marco.spittr.Spittle;
import com.marco.spittr.data.SpittleRepository;

@Controller
@RequestMapping("/spittles")
public class SpittleController {

	private static final String MAX_LONG_AS_STRING = "9223372036854775807";
	private SpittleRepository repository;
	
	@Autowired
	public SpittleController(SpittleRepository rep) {
		this.repository = rep;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String spittles(
			@RequestParam(value="max", defaultValue=MAX_LONG_AS_STRING) long max,
			@RequestParam(value="count", defaultValue="20") int count,
			Model model) {
		model.addAttribute("spittleList",repository.findSpittles(max, count));
		return "spittles";		
	}
	
	
	@RequestMapping(value="/{spittleId}", method=RequestMethod.GET)	
	public String spittle(@PathVariable(value="spittleId") long spittleId, Model  model) {		
		model.addAttribute("spittle", repository.findOne(spittleId));
		return "spittle";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	  public String saveSpittle(SpittleForm form, Model model) throws Exception {
	    repository.save(new Spittle(null, form.getMessage(), new Date(), form.getLongitude(), form.getLatitude()));
	    return "redirect:/spittles";
	  }
}
	