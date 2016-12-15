package com.dplm.simpleDI;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.dplm.simpleDI.exceptions.EmptyConstructorNotFoundException;
import com.dplm.simpleDI.exceptions.UnexpectedInstantiationException;

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
}
