package com.github.stephanelemaire.simpleDI.dependency;

import com.github.stephanelemaire.simpleDI.AutoInject;

public class FirstCircularObject {

	@AutoInject
	public FirstCircularObject(SecondCircularObject secondCircularObject){
		
	}
}
