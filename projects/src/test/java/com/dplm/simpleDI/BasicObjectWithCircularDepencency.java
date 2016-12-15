package com.dplm.simpleDI;

import com.dplm.simpleDI.AutoInject;

public class BasicObjectWithCircularDepencency {

	@AutoInject
	public BasicObjectWithCircularDepencency(ParentObjectWithCircularDepencency parentObjectWithCircularDepencency){
		
	}	
	
}
