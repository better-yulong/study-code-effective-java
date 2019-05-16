package com.zyl.effective.demo.six.expireRef;

import java.util.Arrays;
import java.util.EmptyStackException;

public class ObjectExpireStackMemoryLeak {
	
	private Object[] elements ;
	
	private final static int CAPACITY_INITIAL = 16 ;
	
	private static int size = 0 ;
	
	public ObjectExpireStackMemoryLeak(){
		elements = new Object[CAPACITY_INITIAL];
	}
	
	//Correctly
/*	public Object pop(){
		if(size ==0 ){
			throw new EmptyStackException();
		}
		Object oldValue = elements[size-1];
		elements[--size] = null; // Let gc do its work
    	return oldValue;
	}*/
	
	// Memory Leak
	  public Object pop(){
		if(size ==0 ){
			throw new EmptyStackException();
		}
		return elements[--size] ;
	}
	
	public void push(Object obj){
		ensureCapacity();
		elements[size++] = obj ;
	}
	
	
	/**
	 * ensure space for at least one more element,roughly doubling the capacity each time the array needs to grow
	 * */
	private void ensureCapacity(){
		if(elements.length == size){
			elements  = Arrays.copyOf(elements, 2*size + 1);
		}
		
	}

}
