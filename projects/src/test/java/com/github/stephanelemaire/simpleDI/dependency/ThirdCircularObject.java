package com.github.stephanelemaire.simpleDI.dependency;

import com.github.stephanelemaire.simpleDI.AutoInject;

public class ThirdCircularObject {
	
	@AutoInject
	public ThirdCircularObject(FirstCircularObject firstCircularObject){
		
	}
}
