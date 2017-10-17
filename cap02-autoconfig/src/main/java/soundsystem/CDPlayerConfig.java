package soundsystem;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan
public class CDPlayerConfig {

	@Bean
	@Primary
	public CompactDisc sgtPeppers() {
		return new SgtPeppers();
	}
}
