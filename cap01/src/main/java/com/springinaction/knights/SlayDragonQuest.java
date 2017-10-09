package com.springinaction.knights;

import java.io.PrintStream;

public class SlayDragonQuest implements Quest{

	private PrintStream stream;
	
	public SlayDragonQuest(PrintStream stream) {
		this.stream = stream;
	}
	
	public void embark() {
		stream.println("Emabarking on quest to slay the dragon");
	}

}
