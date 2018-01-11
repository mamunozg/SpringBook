package com.marco.spittr.data;

import java.util.List;

import com.marco.spittr.Spitter;


public interface SpitterRepository {
	
	long count();	
	Spitter save(Spitter spitter);
	Spitter findOne(long id);	
	Spitter findByUserName(String userName);
	List<Spitter> findAll();
	
}
