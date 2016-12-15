package com.dplm.simpleDI;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.HashSet;

import com.dplm.simpleDI.exceptions.CircularDependencyException;
import com.dplm.simpleDI.exceptions.EmptyConstructorNotFoundException;
import com.dplm.simpleDI.exceptions.UnexpectedInstantiationException;

public class Injector {

	private static Injector singleton;	

	private HashMap<Class<?>, Object> instanceMap;
	
	public Injector(){
		instanceMap = new HashMap<Class<?>, Object>();
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T inject(Class<T> classObj){
		
		if(singleton == null){
			singleton = new Injector();
		}
		
		return (T)singleton.checkCacheOrInject(classObj, new HashSet<Class<?>>());
	}
	
	private Object checkCacheOrInject(Class<?> classObj, HashSet<Class<?>> processingStack){
		if(instanceMap.containsKey(classObj)){
			return instanceMap.get(classObj);
		}
		
		circularDependencyChecking(classObj, processingStack);
		
		Object result = resolveInjection(classObj, processingStack);
		instanceMap.put(classObj, result);
		return result;
	}

	private void circularDependencyChecking(Class<?> classObj, HashSet<Class<?>> processingStack){
		if(processingStack.contains(classObj)){
			throw new CircularDependencyException();
		}else{
			processingStack.add(classObj);
		}		
	}
	
	private Object resolveInjection(Class<?> classObj, HashSet<Class<?>> processingStack){
		Constructor<?> constructor = findConstructor(classObj);
		if(constructor.getParameterCount() == 0){
			return resolveEmptyConstructor(constructor);
		}else{
			return resolveInjectedConstructor(constructor, processingStack);
		}
	}
		
	private Constructor<?> findConstructor(Class<?> classObj){
		Constructor<?> emptyConstructor = null;
		for(Constructor<?> constructor : classObj.getConstructors()){
			if(constructor.isAnnotationPresent(AutoInject.class)){
				return constructor;
			}else if(constructor.getParameterCount() == 0){
				emptyConstructor = constructor;
			}
		}
		
		if(emptyConstructor == null){
			throw new EmptyConstructorNotFoundException();			
		}else{
			return emptyConstructor;
		}
	}	
	
	private Object resolveEmptyConstructor(Constructor<?> constructor){
		try {
			return constructor.newInstance();
		} catch (Exception e) {
			throw new UnexpectedInstantiationException();
		}
	}
	
	private Object resolveInjectedConstructor(Constructor<?> constructor, HashSet<Class<?>> processingStack){
		Object[] params = buildConstructorParameter(constructor, processingStack);
		
		try {
			return constructor.newInstance(params);
		} catch (Exception e) {
			throw new UnexpectedInstantiationException();
		}
	}
	
	private Object[] buildConstructorParameter(Constructor<?> constructor, HashSet<Class<?>> processingStack){
		Object[] params = new Object[constructor.getParameterCount()];
		int id = 0;
		for(Class<?> classObj : constructor.getParameterTypes()){
			params[id++] = checkCacheOrInject(classObj, processingStack);
		}
		
		return params;
	}
}
