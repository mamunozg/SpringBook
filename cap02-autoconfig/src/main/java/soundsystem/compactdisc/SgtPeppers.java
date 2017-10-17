package soundsystem.compactdisc;


import org.springframework.stereotype.Component;

@Component
public class SgtPeppers implements CompactDisc {

	private String title = "Titulo del Sgt Pepperes";
	private String autor = "Autor del Sgt Pepperes";
		
	
	public SgtPeppers() {
		super();
	}

	public void play() {		
		System.out.println("Playing " + title + " by " +  autor);
	}

	@Override
	public void playTrack(int numTrack) {
		System.out.println("Playing track number: " + numTrack);		
	}

}
