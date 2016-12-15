package com.dplm.simpleDI.exceptions;

import java.util.HashMap;

public class CircularDependencyException extends RuntimeException{
	private static final long serialVersionUID = -7786435019015624132L;
	
	public CircularDependencyException(Class<?> classObj, Class<?> parentObj, HashMap<Class<?>, Class<?>> dependencyMap){
		StringBuilder sb = new StringBuilder();
		sb.append("[Injector] Circular dependency detected with : ");
		sb.append(classObj.getName());
		while(parentObj != null){
			sb.append(" <- ");
			sb.append(parentObj.getName());
			parentObj = dependencyMap.get(classObj);
		}
		System.out.println(sb.toString());
	}
}
