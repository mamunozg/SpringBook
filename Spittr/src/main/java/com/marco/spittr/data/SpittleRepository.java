package com.marco.spittr.data;

import java.util.List;

import com.marco.spittr.Spittle;

public interface SpittleRepository {
	
	List<Spittle> findRecentSpittles();

	  List<Spittle> findSpittles(long max, int count);
	  
	  Spittle findOne(long id);

	  void save(Spittle spittle);
}
