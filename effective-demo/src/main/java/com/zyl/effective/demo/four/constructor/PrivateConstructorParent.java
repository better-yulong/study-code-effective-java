package com.zyl.effective.demo.four.constructor;

class PrivateConstructorParent {
	
	private PrivateConstructorParent(){
		
	}

}

/*Implicit super constructor PrivateConstructorParent() is not visible for default constructor. 
 * Must define an explicit constructor。即超类的默认构造器不可见，需定义一个明确清晰可见的构造器。
 *
 * 
 *
class PrivateConstructorChild extends PrivateConstructorParent{
	
}*/