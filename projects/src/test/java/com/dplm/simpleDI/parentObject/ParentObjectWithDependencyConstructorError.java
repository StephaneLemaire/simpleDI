package com.dplm.simpleDI.parentObject;

import com.dplm.simpleDI.AutoInject;
import com.dplm.simpleDI.basicObject.BasicObjectWithConstructorError;

public class ParentObjectWithDependencyConstructorError {

	@AutoInject
	public ParentObjectWithDependencyConstructorError(BasicObjectWithConstructorError basicObject){

	}
}
