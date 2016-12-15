package com.dplm.simpleDI.parentObject;

import com.dplm.simpleDI.AutoInject;
import com.dplm.simpleDI.basicObject.BasicObject;

public class ParentObjectWithConstructorError {

	@AutoInject
	public ParentObjectWithConstructorError(BasicObject basicObject){
		int[] arr = new int[2];
		System.out.println(String.valueOf(arr[100]));
	}
}
