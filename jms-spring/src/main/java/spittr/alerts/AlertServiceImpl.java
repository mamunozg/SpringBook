package spittr.alerts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;

import spittr.domain.Spittle;

public class AlertServiceImpl implements AlertService {

	private JmsOperations jmsOperations;
	
	@Autowired
	public AlertServiceImpl(JmsOperations jmsOperations) {	
		this.jmsOperations = jmsOperations;
	}

	@Override
	public void sendSpittleAlert(Spittle spittle) {
		jmsOperations.convertAndSend(spittle);
		
	}

	@Override
	public Spittle retrieveSpittleAlert() {
		// TODO Auto-generated method stub
		return (Spittle)jmsOperations.receiveAndConvert();
	}

}
