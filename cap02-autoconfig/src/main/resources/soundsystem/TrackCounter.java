package soundsystem;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class TrackCounter {

	@Pointcut("execution(* soundsystem.compactdisc.CompactDisc.playTrack(int)) && args(numTrack)")
	public void trackPlayed(int numTrack) {}
	
	private Map<Integer, Integer> countTrack = new HashMap<Integer, Integer>();
	
	@Before("trackPlayed(numTrack)")
	public void countTrack(int numTrack) {		
		countTrack.put(numTrack, getPlayCount(numTrack) + 1);
	}
	
	public int getPlayCount(int trackNumber) {
		return countTrack.containsKey(trackNumber) ? countTrack.get(trackNumber) : 0;
	}
	
}
