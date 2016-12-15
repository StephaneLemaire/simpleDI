package com.dplm.simpleDI.InterfaceObject;

import com.dplm.simpleDI.DefaultInject;
import com.dplm.simpleDI.basicObject.BasicObjectImpl;

@DefaultInject(implementation=BasicObjectImpl.class)
public interface InterfaceObject {

	public int get42();
}
