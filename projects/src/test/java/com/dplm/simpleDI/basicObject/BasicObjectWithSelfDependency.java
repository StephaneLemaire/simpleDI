package com.dplm.simpleDI.basicObject;

import com.dplm.simpleDI.AutoInject;

public class BasicObjectWithSelfDependency {

	@AutoInject
	public BasicObjectWithSelfDependency(BasicObjectWithSelfDependency basicObjectWithSelfDependency){
		
	}
}
