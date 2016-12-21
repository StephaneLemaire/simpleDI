package com.github.stephanelemaire.simpleDI.basicObject;

import com.github.stephanelemaire.simpleDI.AutoInject;

public class BasicObjectWithSelfDependency {

	@AutoInject
	public BasicObjectWithSelfDependency(BasicObjectWithSelfDependency basicObjectWithSelfDependency){
		
	}
}
