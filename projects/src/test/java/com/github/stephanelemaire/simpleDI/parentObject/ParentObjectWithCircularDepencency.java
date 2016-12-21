package com.github.stephanelemaire.simpleDI.parentObject;

import com.github.stephanelemaire.simpleDI.AutoInject;
import com.github.stephanelemaire.simpleDI.basicObject.BasicObjectWithCircularDepencency;

public class ParentObjectWithCircularDepencency {

	@AutoInject
	public ParentObjectWithCircularDepencency(BasicObjectWithCircularDepencency basicObjectWithCircularDepencency){
		
	}
}
