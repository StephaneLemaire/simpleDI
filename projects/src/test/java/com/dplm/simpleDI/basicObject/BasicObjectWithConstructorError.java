package com.dplm.simpleDI.basicObject;

public class BasicObjectWithConstructorError {

	public BasicObjectWithConstructorError(){
		int[] arr = new int[2];
		System.out.println(String.valueOf(arr[100]));
	}
}
