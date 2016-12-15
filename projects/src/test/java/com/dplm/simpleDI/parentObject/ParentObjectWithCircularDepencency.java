package com.dplm.simpleDI.parentObject;

import com.dplm.simpleDI.AutoInject;
import com.dplm.simpleDI.basicObject.BasicObjectWithCircularDepencency;

public class ParentObjectWithCircularDepencency {

	@AutoInject
	public ParentObjectWithCircularDepencency(BasicObjectWithCircularDepencency basicObjectWithCircularDepencency){
		
	}
}
