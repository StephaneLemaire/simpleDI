package com.github.stephanelemaire.simpleDI;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.github.stephanelemaire.simpleDI.Injector;
import com.github.stephanelemaire.simpleDI.InterfaceObject.InterfaceObject;
import com.github.stephanelemaire.simpleDI.InterfaceObject.InterfaceWithWrongImplementation;
import com.github.stephanelemaire.simpleDI.InterfaceObject.InterfaceWithoutDefaultInjection;
import com.github.stephanelemaire.simpleDI.basicObject.BasicObject;
import com.github.stephanelemaire.simpleDI.basicObject.BasicObjectWithConstructorError;
import com.github.stephanelemaire.simpleDI.basicObject.BasicObjectWithSelfDependency;
import com.github.stephanelemaire.simpleDI.basicObject.BasicObjectWithoutEmptyConstructor;
import com.github.stephanelemaire.simpleDI.basicObject.ImplementationOfInterfaceWithoutDefaultInjection;
import com.github.stephanelemaire.simpleDI.dependency.FirstCircularObject;
import com.github.stephanelemaire.simpleDI.exceptions.CircularDependencyException;
import com.github.stephanelemaire.simpleDI.exceptions.EmptyConstructorNotFoundException;
import com.github.stephanelemaire.simpleDI.exceptions.InterfaceImplementationNotFoundException;
import com.github.stephanelemaire.simpleDI.exceptions.MissingDefaultInjectionException;
import com.github.stephanelemaire.simpleDI.exceptions.UnexpectedInstantiationException;
import com.github.stephanelemaire.simpleDI.parentObject.ParentObject;
import com.github.stephanelemaire.simpleDI.parentObject.ParentObjectWithCircularDepencency;
import com.github.stephanelemaire.simpleDI.parentObject.ParentObjectWithConstructorError;
import com.github.stephanelemaire.simpleDI.parentObject.ParentObjectWithDependencyConstructorError;

public class InjectorShould {

	@Rule
	public ExpectedException thrown = ExpectedException.none();		
	
	@Test
	public void instantiate_onInject_whenNoConstructorProvided(){
		BasicObject basicObject = Injector.inject(BasicObject.class);
		Assert.assertEquals(42, basicObject.get42());
	}
	
	@Test
	public void throw_onInject_whenNoEmptyConstructorFound(){
		thrown.expect(EmptyConstructorNotFoundException.class);
		Injector.inject(BasicObjectWithoutEmptyConstructor.class);
	}

	@Test
	public void throw_onInject_whenObjectInstantiationFails(){
		thrown.expect(UnexpectedInstantiationException.class);
		Injector.inject(BasicObjectWithConstructorError.class);
	}
	
	@Test
	public void useCache_onInject_whenObjectCalledMoreThanOnce(){
		BasicObject basicObject = Injector.inject(BasicObject.class);
		BasicObject sameBasicObject = Injector.inject(BasicObject.class);
		Assert.assertEquals(basicObject, sameBasicObject);
	}
	
	@Test
	public void inject_onInject_whenObjectSpecifyAutoInjection(){
		ParentObject parentObject = Injector.inject(ParentObject.class);
		Assert.assertEquals(42, parentObject.get42());
	}
	
	@Test
	public void throw_onInject_whenInjectedInstantiationFails(){
		thrown.expect(UnexpectedInstantiationException.class);
		Injector.inject(ParentObjectWithDependencyConstructorError.class);
	}
	
	@Test
	public void throw_onInject_whenParentObjectInstantiationFails(){
		thrown.expect(UnexpectedInstantiationException.class);
		Injector.inject(ParentObjectWithConstructorError.class);
	}	
	
	@Test
	public void throw_onInject_whenCircularDependencyDetected(){
		thrown.expect(CircularDependencyException.class);
		Injector.inject(ParentObjectWithCircularDepencency.class);
	}

	@Test
	public void throw_onInject_whenBiggerCircularDependencyDetected(){
		thrown.expect(CircularDependencyException.class);
		Injector.inject(FirstCircularObject.class);
	}
	
	@Test
	public void throw_onInject_whenObjectDependingOnItself(){
		thrown.expect(CircularDependencyException.class);
		Injector.inject(BasicObjectWithSelfDependency.class);		
	}
	
	@Test
	public void throw_onInject_whenInterfaceWithoutDefaultInjection(){
		thrown.expect(MissingDefaultInjectionException.class);
		Injector.inject(InterfaceWithoutDefaultInjection.class);
	}

	@Test
	public void throw_onInject_whenInterfaceWithoutDefaultInjectionImplementation(){
		thrown.expect(MissingDefaultInjectionException.class);
		Injector.inject(InterfaceWithoutDefaultInjection.class);
	}
	
	@Test
	public void inject_onInject_whenAnnotatedInterfaceProvided(){
		InterfaceObject interfaceObject = Injector.inject(InterfaceObject.class);
		Assert.assertEquals(42, interfaceObject.get42());
	}	
	
	@Test
	public void throw_onInject_whenInterfaceImplementationMissing(){
		thrown.expect(InterfaceImplementationNotFoundException.class);
		Injector.inject(InterfaceWithWrongImplementation.class);
	}
	
	@Test
	public void inject_onInject_whenInterfaceImplementationExplicit(){
		Injector.addImplementation(InterfaceWithoutDefaultInjection.class, ImplementationOfInterfaceWithoutDefaultInjection.class);
		InterfaceWithoutDefaultInjection object = Injector.inject(InterfaceWithoutDefaultInjection.class);
		Assert.assertEquals(42, object.get42());
	}
	
	@Test
	public void throw_onInject_whenAddingIncompatibleInterfaceAndImplementation(){
		thrown.expect(InterfaceImplementationNotFoundException.class);
		Injector.addImplementation(InterfaceWithoutDefaultInjection.class, BasicObject.class);
	}
}
