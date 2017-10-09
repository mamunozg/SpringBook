package com.springinaction.knights;

import java.io.PrintStream;

public class Minstrel {

	private PrintStream stream;

	public Minstrel(PrintStream stream) {
		super();
		this.stream = stream;
	}
	
	public void singBeforeQuest() {
		stream.println("Singing before the quest ....");
	}
	
	public void singAfterQuest() {
		stream.println("Singing after the quest ....");
	}	
}
