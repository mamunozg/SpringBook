package config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


import soundsystem.compactdisc.CompactDisc;
import soundsystem.compactdisc.SgtPeppers;
import soundsystem.mediaplayer.CDPlayer;
import soundsystem.mediaplayer.MediaPlayer;

@Configuration
@ComponentScan
public class CDPlayerConfig {

	@Bean
	public CompactDisc cd() {
		return new SgtPeppers();
	}

	@Bean
	public MediaPlayer mp(CompactDisc cd) {
		return new CDPlayer(cd);
	}
}
