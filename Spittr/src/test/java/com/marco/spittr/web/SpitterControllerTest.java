package com.marco.spittr.web;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import com.marco.spittr.Spitter;
import com.marco.spittr.data.SpitterRepository;

public class SpitterControllerTest {

	@Test
	public void shouldShowRegistration() throws Exception {
		
		SpitterRepository repository = mock(SpitterRepository.class);		
		SpitterController controller = new SpitterController(repository);			
		MockMvc mockMvc = standaloneSetup(controller).build();		
		
		mockMvc.perform(get("/spitter/register")).andExpect(view().name("registerForm"));		
	}
	
	@Test
	public void shouldProcessRegistration() throws Exception {
		
		SpitterRepository repository = mock(SpitterRepository.class);
		
		Spitter spitterUnsaved = new Spitter("mamunozg", "passpass", "Marco Antonio", "Muñoz Garcia Gasco", "kk@kk.es");
		Spitter spitterSaved = new Spitter(1l, "mamunozg", "passpass", "Marco Antonio", "Muñoz Garcia Gasco", "kk@kk.es");
		
		when(repository.save(spitterUnsaved)).thenReturn(spitterSaved);
		
		SpitterController controller = new SpitterController(repository);		
		MockMvc mockMvc = standaloneSetup(controller).build();		
		
		mockMvc.perform(post("/spitter/register")
				.param("userName", "mamunozg")
				.param("password", "passpass")
				.param("firstName", "Marco Antonio")
				.param("lastName", "Muñoz Garcia Gasco")								
				.param("email", "kk@kk.es")
				).andExpect(redirectedUrl("/spitter/mamunozg"));
		
													
		verify(repository, atLeastOnce()).save(spitterUnsaved);
						
	}
	
	@Test
	public void shouldFailValidationWithNoData() throws Exception {
		SpitterRepository repository = mock(SpitterRepository.class);
		SpitterController controller = new SpitterController(repository);
		MockMvc mockMvc = standaloneSetup(controller).build();
		
		mockMvc.perform(post("/spitter/register"))
			.andExpect(status().isOk())
			.andExpect(view().name("registerForm"))
			.andExpect(model().errorCount(5))
			.andExpect(model().attributeHasFieldErrors(
					"spitter", "userName", "password", "firstName", "lastName", "email"));
		
		
	}
	
	
}
