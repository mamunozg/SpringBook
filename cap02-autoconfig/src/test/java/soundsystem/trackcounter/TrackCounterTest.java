package soundsystem.trackcounter;


import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.TrackCounterConfig;
import soundsystem.TrackCounter;
import soundsystem.compactdisc.CompactDisc;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TrackCounterConfig.class)
public class TrackCounterTest {

	@Rule
	public final StandardOutputStreamLog log = new StandardOutputStreamLog();
	
	@Autowired	
	private CompactDisc sgtPeppers; 
	
	@Autowired
	private TrackCounter counter;
	
	@Test
	public void testTrackCounter() {
		sgtPeppers.playTrack(1);
		sgtPeppers.playTrack(2);
		sgtPeppers.playTrack(2);
		sgtPeppers.playTrack(2);
		sgtPeppers.playTrack(2);
		sgtPeppers.playTrack(4);
		sgtPeppers.playTrack(4);
		sgtPeppers.playTrack(4);
				
		
		assertEquals(1, counter.getPlayCount(1));
		assertEquals(4, counter.getPlayCount(2));
		assertEquals(3, counter.getPlayCount(4));
		assertEquals(0, counter.getPlayCount(50));
		
	}

}
