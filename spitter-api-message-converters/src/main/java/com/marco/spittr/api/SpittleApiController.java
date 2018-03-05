package com.marco.spittr.api;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.marco.spittr.Spittle;
import com.marco.spittr.data.SpittleNotFoundException;
import com.marco.spittr.data.SpittleRepository;

@RestController
@RequestMapping("/spittles")
public class SpittleApiController {

	
	private static final String MAX_LONG_AS_STRING = "9223372036854775807";
	
	private SpittleRepository spittleRepository;

	public SpittleApiController() {
		
	}
	
	@Autowired
	public SpittleApiController(SpittleRepository spittleRepository) {
		this.spittleRepository = spittleRepository;
	}
	
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Spittle> spittles(
			@RequestParam(value="max", defaultValue=MAX_LONG_AS_STRING) long max,
			@RequestParam(value="count", defaultValue="20") int count
			) {
		
		return spittleRepository.findSpittles(max, count);
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public Spittle spittleById(@PathVariable long id) {
		return spittleRepository.findOne(id);
	}
			
	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Spittle> saveSpittle(@RequestBody Spittle spittle, UriComponentsBuilder ucb) {
		
		Spittle saved = spittleRepository.save(spittle);
		
		URI locationURI = ucb.path("/spittles").path(String.valueOf(saved.getId())).build().toUri();
		
		HttpHeaders headers  = new HttpHeaders();
		headers.setLocation(locationURI);
		
		
		ResponseEntity<Spittle> responseEntity = new ResponseEntity<Spittle>(saved, headers, HttpStatus.CREATED);
		return responseEntity;		
		
	}
	
	@ExceptionHandler(SpittleNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody Error spittleNotFound(SpittleNotFoundException e) {
		long spittleId = e.getSpittleId();
		return new Error(4, "Spittle [" + spittleId + "] not found");
	}
	
	
}
