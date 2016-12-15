package com.dplm.simpleDI;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.dplm.simpleDI.InterfaceObject.InterfaceObject;
import com.dplm.simpleDI.InterfaceObject.InterfaceWithoutDefaultInjection;
import com.dplm.simpleDI.basicObject.BasicObject;
import com.dplm.simpleDI.basicObject.BasicObjectWithConstructorError;
import com.dplm.simpleDI.basicObject.BasicObjectWithSelfDependency;
import com.dplm.simpleDI.basicObject.BasicObjectWithoutEmptyConstructor;
import com.dplm.simpleDI.dependency.FirstCircularObject;
import com.dplm.simpleDI.exceptions.CircularDependencyException;
import com.dplm.simpleDI.exceptions.EmptyConstructorNotFoundException;
import com.dplm.simpleDI.exceptions.MissingDefaultInjectionException;
import com.dplm.simpleDI.exceptions.UnexpectedInstantiationException;
import com.dplm.simpleDI.parentObject.ParentObject;
import com.dplm.simpleDI.parentObject.ParentObjectWithCircularDepencency;
import com.dplm.simpleDI.parentObject.ParentObjectWithConstructorError;
import com.dplm.simpleDI.parentObject.ParentObjectWithDependencyConstructorError;

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
}
