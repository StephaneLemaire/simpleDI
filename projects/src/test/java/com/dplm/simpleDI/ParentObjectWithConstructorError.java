package com.dplm.simpleDI;

public class ParentObjectWithConstructorError {

	@AutoInject
	public ParentObjectWithConstructorError(BasicObject basicObject){
		int[] arr = new int[2];
		System.out.println(String.valueOf(arr[100]));
	}
}
