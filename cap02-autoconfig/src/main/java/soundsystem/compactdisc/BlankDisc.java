package soundsystem.compactdisc;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("blank")
public class BlankDisc implements CompactDisc {

	private String title;
	private String artist;
	private List<String> tracks;

	public BlankDisc(String title, String artist, List<String> tracks) {			
		this.title = title;
		this.artist = artist;
		this.tracks = tracks;
	}

	@Override
	public void play() {
		System.out.println("Playing " + title + " by " + artist);
		for (String track : tracks) {
			System.out.println("-Track: " + track);
		}

	}

	@Override
	public void playTrack(int numTrack) {
	    System.out.println("Playing numTrack --> " + numTrack);
	}

}
