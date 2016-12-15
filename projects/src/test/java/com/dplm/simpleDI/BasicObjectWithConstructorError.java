package com.dplm.simpleDI;

public class BasicObjectWithConstructorError {

	public BasicObjectWithConstructorError(){
		int[] arr = new int[2];
		System.out.println(String.valueOf(arr[100]));
	}
}
