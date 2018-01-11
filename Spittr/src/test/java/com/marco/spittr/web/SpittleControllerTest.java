package com.marco.spittr.web;

import static org.hamcrest.Matchers.hasItems;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import com.marco.spittr.Spittle;
import com.marco.spittr.data.SpittleRepository;

public class SpittleControllerTest {

	@Test
	public void shouldShowRecentSpittles() throws Exception {
		
		SpittleRepository mockRepository = mock(SpittleRepository.class);
		
		List<Spittle> listSpittle = createSpitteList(20);		
		when(mockRepository.findSpittles(Long.MAX_VALUE, 20)).thenReturn(listSpittle);		
		
		SpittleController controller = new SpittleController(mockRepository);
		MockMvc mock = standaloneSetup(controller).setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp")).build();
		mock.perform(get("/spittles"))
			.andExpect(view().name("spittles"))
			.andExpect(model().attributeExists("spittleList"))
			.andExpect(model().attribute("spittleList", hasItems(listSpittle.toArray())));
		
	}
	
	@Test
	public void shouldShowPagedSpittles() throws Exception {
		List<Spittle> listSpittle = createSpitteList(50);
		SpittleRepository mockrepRepository = mock(SpittleRepository.class);
		when(mockrepRepository.findSpittles(238900l, 50)).thenReturn(listSpittle);
		
		SpittleController controller = new SpittleController(mockrepRepository);
		
		MockMvc mockMvc = standaloneSetup(controller).setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp")).build();
		
		mockMvc.perform(get("/spittles?max=238900&count=50"))
		.andExpect(view().name("spittles"))
		.andExpect(model().attributeExists("spittleList"))
		.andExpect(model().attribute("spittleList", hasItems(listSpittle.toArray())));
	}
	
	
	@Test
	public void testSpittle() throws Exception {
		
		SpittleRepository repository = mock(SpittleRepository.class);
		
		Spittle spittle = new Spittle("Hello", new Date());
		
		when(repository.findOne(12345)).thenReturn(spittle);
		
		SpittleController controller = new SpittleController(repository);
		MockMvc mockMvc = standaloneSetup(controller).build();
		
		mockMvc.perform(get("/spittles/12345"))
			.andExpect(view().name("spittle"))
			.andExpect(model().attributeExists("spittle"))
			.andExpect(model().attribute("spittle", spittle));
				
	}
	
	public void saveSpittle() throws Exception {
		
		SpittleRepository mockRepository = mock(SpittleRepository.class);
		SpittleController controller = new SpittleController(mockRepository);
		MockMvc mockMvc = standaloneSetup(controller).build();
		
		mockMvc.perform(get("/spittles")
				.param("message", "Hello World!!!!")
				.param("longitude", "-78.12312")
				.param("latitude", "38.12312")
				)
			.andExpect(redirectedUrl("/spittles"));
		
		verify(mockRepository, atLeastOnce()).save(new Spittle(null,  "Hello World!!!!", new Date(), -78.12312, 38.12312));
	}
	
	
	
	private List<Spittle> createSpitteList(int num) {
		List<Spittle> listSpittles = new ArrayList<>(num); 
		for(int i=0;i<num;i++) {
			listSpittles.add(new Spittle("Spittle " + i, new Date()));
		}
		return listSpittles;
	}
}
