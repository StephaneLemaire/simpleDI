package com.dplm.simpleDI.parentObject;

import com.dplm.simpleDI.AutoInject;
import com.dplm.simpleDI.basicObject.BasicObject;

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
