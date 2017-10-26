package com.marco.spittr.data;

import com.marco.spittr.Spitter;


public interface SpitterRepository {
	
	Spitter save(Spitter spitter);
	Spitter findByUserName(String userName);
	
}
