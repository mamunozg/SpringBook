package org.marco.concert;

import org.springframework.stereotype.Component;

@Component
public class Theatre implements Performance{

	@Override
	public void perform()  {
		System.out.println("Esto es el teatro");			
	}

}
