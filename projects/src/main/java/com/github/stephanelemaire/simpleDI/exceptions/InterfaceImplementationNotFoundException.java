package com.github.stephanelemaire.simpleDI.exceptions;

public class InterfaceImplementationNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 731930133805599677L;

	public InterfaceImplementationNotFoundException(Class<?> itf, Class<?> impl){
		System.out.println("[Injector] class " + impl.getCanonicalName() + " doesn't implement the interface " + itf.getCanonicalName());
	}
}
