package com.marco.spittr.web;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
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
		
		Spitter spitterUnsaved = new Spitter("mamunozg", "pass", "Marco Antonio", "Muñoz Garcia Gasco", "kk@kk.es");
		Spitter spitterSaved = new Spitter(1l, "mamunozg", "pass", "Marco Antonio", "Muñoz Garcia Gasco", "kk@kk.es");

		SpitterRepository repository = mock(SpitterRepository.class);
		when(repository.save(spitterUnsaved)).thenReturn(spitterSaved);
		
		SpitterController controller = new SpitterController(repository);		
		MockMvc mockMvc = standaloneSetup(controller).build();
		
		
		mockMvc.perform(post("/spitter/register")
				.param("firstName", "Marco Antonio")
				.param("lastName", "Muñoz Garcia Gasco")
				.param("userName", "mamunozg")
				.param("password", "pass")
				.param("email", "kk@kk.es")
				).andExpect(redirectedUrl("/spitter/mamunozg"));
		
													
		verify(repository, atLeastOnce()).save(spitterUnsaved);
				
		
	}
	
}
