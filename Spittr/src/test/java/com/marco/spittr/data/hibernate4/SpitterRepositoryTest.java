package com.marco.spittr.data.hibernate4;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.marco.spittr.data.SpitterRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=RepositoryTestConfig.class)
public class SpitterRepositoryTest {

	@Autowired
	SpitterRepository repository;
		
	@Test
	@Transactional
	public void count() {
		assertEquals(4, repository.count());
		
	}
	
	
}
