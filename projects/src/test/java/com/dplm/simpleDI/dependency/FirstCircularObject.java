package com.dplm.simpleDI.dependency;

import com.dplm.simpleDI.AutoInject;

public class FirstCircularObject {

	@AutoInject
	public FirstCircularObject(SecondCircularObject secondCircularObject){
		
	}
}
