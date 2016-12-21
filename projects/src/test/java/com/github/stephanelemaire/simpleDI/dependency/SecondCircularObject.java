package com.github.stephanelemaire.simpleDI.dependency;

import com.github.stephanelemaire.simpleDI.AutoInject;

public class SecondCircularObject {

	@AutoInject
	public SecondCircularObject(ThirdCircularObject thirdCircularObject){
		
	}
}
