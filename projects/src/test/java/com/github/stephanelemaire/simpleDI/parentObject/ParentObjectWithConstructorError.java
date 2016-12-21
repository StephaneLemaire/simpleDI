package com.github.stephanelemaire.simpleDI.parentObject;

import com.github.stephanelemaire.simpleDI.AutoInject;
import com.github.stephanelemaire.simpleDI.basicObject.BasicObject;

public class ParentObjectWithConstructorError {

	@AutoInject
	public ParentObjectWithConstructorError(BasicObject basicObject){
		int[] arr = new int[2];
		System.out.println(String.valueOf(arr[100]));
	}
}
