package com.dplm.simpleDI;

import java.lang.reflect.Constructor;

import com.dplm.simpleDI.exceptions.EmptyConstructorNotFoundException;
import com.dplm.simpleDI.exceptions.UnexpectedInstantiationException;

public class Injector {

	private static Injector singleton;	
	
	@SuppressWarnings("unchecked")
	public static <T> T inject(Class<T> classObj){
		
		if(singleton == null){
			singleton = new Injector();
		}
		
		return (T)singleton.resolveObjectWithEmptyConstructor(classObj);
	}
	
	private Object resolveObjectWithEmptyConstructor(Class<?> classObj){
		Constructor<?> emptyConstructor = findEmptyConstructor(classObj);
		return resolveEmptyConstructor(emptyConstructor);
	}
	
	private Constructor<?> findEmptyConstructor(Class<?> classObj){
		for(Constructor<?> constructor : classObj.getConstructors()){
			if(constructor.getParameterCount() == 0){
				return constructor;
			}
		}
		throw new EmptyConstructorNotFoundException();
	}	
	
	private Object resolveEmptyConstructor(Constructor<?> constructor){
		try {
			return constructor.newInstance();
		} catch (Exception e) {
			throw new UnexpectedInstantiationException();
		}
	}
}