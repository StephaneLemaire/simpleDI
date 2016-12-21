package com.dplm.simpleDI;

import java.lang.reflect.Constructor;
import java.util.HashMap;

import com.dplm.simpleDI.exceptions.CircularDependencyException;
import com.dplm.simpleDI.exceptions.EmptyConstructorNotFoundException;
import com.dplm.simpleDI.exceptions.InterfaceImplementationNotFoundException;
import com.dplm.simpleDI.exceptions.MissingDefaultInjectionException;
import com.dplm.simpleDI.exceptions.UnexpectedInstantiationException;

public class Injector {

	private static Injector singleton;	

	private HashMap<Class<?>, Object> instanceMap;
	private HashMap<Class<?>, Class<?>> additionnalInterfaceMap;
	
	public Injector(){
		instanceMap = new HashMap<Class<?>, Object>();
		additionnalInterfaceMap = new HashMap<Class<?>, Class<?>>();
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T inject(Class<T> classObj){
		Injector injector = getSingleton();
		return (T)injector.checkCacheOrInject(classObj, null, new HashMap<Class<?>, Class<?>>());
	}
	
	public static void addImplementation(Class<?> interfaceObj, Class<?> classObj){
		Injector injector = getSingleton();
		injector.addAdditionnalInterfaceImplementation(interfaceObj, classObj);
	}

	private static Injector getSingleton(){
		if(singleton == null){
			singleton = new Injector();
		}
		return singleton;
	}
	
	private void addAdditionnalInterfaceImplementation(Class<?> interfaceObj, Class<?> classObj){
		checkClassIsImplementingInterface(classObj, interfaceObj);
		
		additionnalInterfaceMap.put(interfaceObj, classObj);
	}
		
	private Object checkCacheOrInject(Class<?> classObj, Class<?> parentClassObj, HashMap<Class<?>, Class<?>> dependencyMap){
		if(instanceMap.containsKey(classObj)){
			return instanceMap.get(classObj);
		}
		
		circularDependencyChecking(classObj, parentClassObj, dependencyMap);
		
		Object result = resolveInjection(classObj, dependencyMap);
		instanceMap.put(classObj, result);
		return result;
	}

	private void circularDependencyChecking(Class<?> classObj, Class<?> parentClassObj, HashMap<Class<?>, Class<?>> dependencyMap){
		if(dependencyMap.containsKey(classObj)){
			throw new CircularDependencyException(classObj, parentClassObj, dependencyMap);
		}else{
			dependencyMap.put(classObj, parentClassObj);
		}
	}
	
	private Object resolveInjection(Class<?> classObj, HashMap<Class<?>, Class<?>> dependencyMap){
		if(classObj.isInterface()){
			return resolveInterface(classObj, dependencyMap);
		}else{
			return resolveObject(classObj, dependencyMap);
		}
	}
		
	private Object resolveInterface(Class<?> itfObj, HashMap<Class<?>, Class<?>> dependencyMap){
		Class<?> classObj = null;
		if(itfObj.isAnnotationPresent(DefaultInject.class)){
			DefaultInject annotation = itfObj.getAnnotation(DefaultInject.class);	
			classObj = annotation.implementedBy();
		}else if(additionnalInterfaceMap.containsKey(itfObj)){
			classObj = additionnalInterfaceMap.get(itfObj);
		}else{
			throw new MissingDefaultInjectionException();			
		}
			
		return resolveInterfaceImplementation(itfObj, classObj, dependencyMap);
	}	
	
	private Object resolveInterfaceImplementation(Class<?> itfObj, Class<?> classObj, HashMap<Class<?>, Class<?>> dependencyMap){
		checkClassIsImplementingInterface(classObj, itfObj);
		return checkCacheOrInject(classObj, itfObj, dependencyMap);
		
	}
	
	private void checkClassIsImplementingInterface(Class<?> impl, Class<?> itf){
		for(Class<?> implItf : impl.getInterfaces()){
			if(implItf == itf){
				return;
			}
		}
		
		throw new InterfaceImplementationNotFoundException(itf, impl);
	}
	
	private Object resolveObject(Class<?> classObj, HashMap<Class<?>, Class<?>> dependencyMap){
		Constructor<?> constructor = findConstructor(classObj);
		if(getContructorParameterCount(constructor) == 0){
			return resolveEmptyConstructor(constructor);
		}else{
			return resolveInjectedConstructor(constructor, dependencyMap);
		}
	}
	
	private Constructor<?> findConstructor(Class<?> classObj){
		Constructor<?> emptyConstructor = null;
		for(Constructor<?> constructor : classObj.getConstructors()){
			if(constructor.isAnnotationPresent(AutoInject.class)){
				return constructor;
			}else if(getContructorParameterCount(constructor) == 0){
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
	
	private Object resolveInjectedConstructor(Constructor<?> constructor, HashMap<Class<?>, Class<?>> dependencyMap){
		Object[] params = buildConstructorParameter(constructor, dependencyMap);
		
		try {
			return constructor.newInstance(params);
		} catch (Exception e) {
			throw new UnexpectedInstantiationException();
		}
	}
	
	private Object[] buildConstructorParameter(Constructor<?> constructor, HashMap<Class<?>, Class<?>> dependencyMap){
		Object[] params = new Object[getContructorParameterCount(constructor)];
		int id = 0;
		for(Class<?> classObj : constructor.getParameterTypes()){
			params[id++] = checkCacheOrInject(classObj, constructor.getDeclaringClass(), dependencyMap);
		}
		
		return params;
	}
	
	private int getContructorParameterCount(Constructor<?> constructor){
		return constructor.getParameterTypes().length;
	}
}
