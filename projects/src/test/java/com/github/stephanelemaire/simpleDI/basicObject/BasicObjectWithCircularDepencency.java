package com.github.stephanelemaire.simpleDI.basicObject;

import com.github.stephanelemaire.simpleDI.AutoInject;
import com.github.stephanelemaire.simpleDI.parentObject.ParentObjectWithCircularDepencency;

public class BasicObjectWithCircularDepencency {

	@AutoInject
	public BasicObjectWithCircularDepencency(ParentObjectWithCircularDepencency parentObjectWithCircularDepencency){
		
	}	
	
}
