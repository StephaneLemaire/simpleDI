package com.github.stephanelemaire.simpleDI.basicObject;

import com.github.stephanelemaire.simpleDI.InterfaceObject.InterfaceWithoutDefaultInjection;

public class ImplementationOfInterfaceWithoutDefaultInjection implements InterfaceWithoutDefaultInjection{

	@Override
	public int get42() {
		return 42;
	}

}
