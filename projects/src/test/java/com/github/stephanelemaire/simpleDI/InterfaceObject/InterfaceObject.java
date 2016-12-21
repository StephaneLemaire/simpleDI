package com.github.stephanelemaire.simpleDI.InterfaceObject;

import com.github.stephanelemaire.simpleDI.DefaultInject;
import com.github.stephanelemaire.simpleDI.basicObject.BasicObjectImpl;

@DefaultInject(implementedBy=BasicObjectImpl.class)
public interface InterfaceObject {

	public int get42();
}
