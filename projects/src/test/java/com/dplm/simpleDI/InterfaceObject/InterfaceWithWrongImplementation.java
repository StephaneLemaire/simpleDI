package com.dplm.simpleDI.InterfaceObject;

import com.dplm.simpleDI.DefaultInject;
import com.dplm.simpleDI.basicObject.BasicObjectWithoutImpl;

@DefaultInject(implementedBy=BasicObjectWithoutImpl.class)
public interface InterfaceWithWrongImplementation {

}
