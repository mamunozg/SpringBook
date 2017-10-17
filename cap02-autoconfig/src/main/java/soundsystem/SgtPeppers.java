package soundsystem;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class SgtPeppers implements CompactDisc {

	private String title = "Titulo del Sgt Pepperes";
	private String autor = "Autor del Sgt Pepperes";
	
	public void play() {		
		System.out.println("Playing " + title + " by " +  autor);
	}

}
