package com.dplm.simpleDI.dependency;

import com.dplm.simpleDI.AutoInject;

public class ThirdCircularObject {
	
	@AutoInject
	public ThirdCircularObject(FirstCircularObject firstCircularObject){
		
	}
}
