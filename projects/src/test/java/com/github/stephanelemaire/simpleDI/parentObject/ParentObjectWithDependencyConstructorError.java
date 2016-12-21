package com.github.stephanelemaire.simpleDI.parentObject;

import com.github.stephanelemaire.simpleDI.AutoInject;
import com.github.stephanelemaire.simpleDI.basicObject.BasicObjectWithConstructorError;

public class ParentObjectWithDependencyConstructorError {

	@AutoInject
	public ParentObjectWithDependencyConstructorError(BasicObjectWithConstructorError basicObject){

	}
}
