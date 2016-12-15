package com.dplm.simpleDI;

import com.dplm.simpleDI.AutoInject;

public class ParentObjectWithCircularDepencency {

	@AutoInject
	public ParentObjectWithCircularDepencency(BasicObjectWithCircularDepencency basicObjectWithCircularDepencency){
		
	}
}
