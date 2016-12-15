package com.dplm.simpleDI;

public class BasicObjectWithoutEmptyConstructor {

	private int magicNumber;
	
	public BasicObjectWithoutEmptyConstructor(int magicNumber){
		this.magicNumber = magicNumber;
	}
	
	public int getMagicNumber(){
		return magicNumber;
	}
}
