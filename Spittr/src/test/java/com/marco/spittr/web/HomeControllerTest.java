package com.marco.spittr.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;


public class HomeControllerTest {
//
//	@Test
//	public void testHome() throws Exception {
//		HomeController controller = new HomeController();
//		assertEquals("home", controller.home());
//	}
//	
	@Test
	public void testHomePage() throws Exception {
		HomeController controller = new HomeController();
		MockMvc mock = standaloneSetup(controller).build();
		mock.perform(get("/")).andExpect(view().name("home"));
		
	}
		
}
