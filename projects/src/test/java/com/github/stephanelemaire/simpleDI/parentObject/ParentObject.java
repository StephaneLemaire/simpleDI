package com.github.stephanelemaire.simpleDI.parentObject;

import com.github.stephanelemaire.simpleDI.AutoInject;
import com.github.stephanelemaire.simpleDI.basicObject.BasicObject;

public class ParentObject {

	private BasicObject basicObject;
	
	@AutoInject
	public ParentObject(BasicObject basicObject){
		this.basicObject = basicObject;
	}
	
	public int get42(){
		return basicObject.get42();
	}
}
