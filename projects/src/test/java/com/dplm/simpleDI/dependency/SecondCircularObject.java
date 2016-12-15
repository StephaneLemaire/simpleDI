package com.dplm.simpleDI.dependency;

import com.dplm.simpleDI.AutoInject;

public class SecondCircularObject {

	@AutoInject
	public SecondCircularObject(ThirdCircularObject thirdCircularObject){
		
	}
}
