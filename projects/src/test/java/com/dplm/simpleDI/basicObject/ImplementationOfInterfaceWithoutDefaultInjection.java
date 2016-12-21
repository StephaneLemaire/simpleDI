package com.dplm.simpleDI.basicObject;

import com.dplm.simpleDI.InterfaceObject.InterfaceWithoutDefaultInjection;

public class ImplementationOfInterfaceWithoutDefaultInjection implements InterfaceWithoutDefaultInjection{

	@Override
	public int get42() {
		return 42;
	}

}
