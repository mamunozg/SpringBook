package config;

import java.util.ArrayList;
import java.util.List;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import soundsystem.TrackCounter;
import soundsystem.compactdisc.BlankDisc;
import soundsystem.compactdisc.CompactDisc;
import soundsystem.compactdisc.SgtPeppers;

@Configuration
@EnableAspectJAutoProxy
public class TrackCounterConfig {

	@Bean
	public CompactDisc sgtPeppers() {	
		return new BlankDisc("Sgt. Pepper's Lonely Hearts Club Band", "The Beatles", getListTracks());		
	}
	
	
	@Bean
	public TrackCounter trackCounter() {
		return new TrackCounter();
	}
	
	private List<String> getListTracks() {
		List<String> tracks = new ArrayList<String>();
		tracks.add("Sgt. Pepper's Lonely Hearts Club Band");
		tracks.add("With a Little Help from My Friends");
		tracks.add("Lucy in the Sky with Diamonds");
		tracks.add("Getting Better");
		tracks.add("Fixing a Hole");
		return tracks;
	}
	
}
