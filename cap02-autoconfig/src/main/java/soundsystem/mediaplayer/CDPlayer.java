package soundsystem.mediaplayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import soundsystem.compactdisc.CompactDisc;

@Component
public class CDPlayer implements MediaPlayer {
	
	
	private CompactDisc cd;
	
	@Autowired
	public CDPlayer(CompactDisc cd) {
		this.cd = cd;
	}

	@Override
	public void play() {
		cd.play();

	}
}
