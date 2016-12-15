package com.dplm.simpleDI.basicObject;

import com.dplm.simpleDI.AutoInject;
import com.dplm.simpleDI.parentObject.ParentObjectWithCircularDepencency;

public class BasicObjectWithCircularDepencency {

	@AutoInject
	public BasicObjectWithCircularDepencency(ParentObjectWithCircularDepencency parentObjectWithCircularDepencency){
		
	}	
	
}
