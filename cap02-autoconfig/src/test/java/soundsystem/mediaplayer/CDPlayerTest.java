package soundsystem.mediaplayer;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.CDPlayerConfig;
import soundsystem.compactdisc.CompactDisc;
import soundsystem.mediaplayer.MediaPlayer;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=CDPlayerConfig.class)
public class CDPlayerTest {

	@Rule
	public final  StandardOutputStreamLog log =new StandardOutputStreamLog();
	
	@Autowired
	private MediaPlayer mp;
		
	@Autowired
	private CompactDisc cd;

	
	@Test
	public void cdShoulNotBeEmpty() {
		assertNotNull(cd);
	}
	
	@Test
	public void play() {
		mp.play();
		assertEquals("Playing Titulo del Sgt Pepperes by Autor del Sgt Pepperes\r\n",log.getLog());
	}
	

}
