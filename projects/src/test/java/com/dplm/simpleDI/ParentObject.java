package com.dplm.simpleDI;

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
