package com.github.stephanelemaire.simpleDI.InterfaceObject;

import com.github.stephanelemaire.simpleDI.DefaultInject;
import com.github.stephanelemaire.simpleDI.basicObject.BasicObjectWithoutImpl;

@DefaultInject(implementedBy=BasicObjectWithoutImpl.class)
public interface InterfaceWithWrongImplementation {

}
