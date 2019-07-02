package com.aoe.demo.sample1;

import java.util.UUID;

//import org.springframework.stereotype.Service;

//@Service(value="BeanExample,BeanExample1")
public class BeanExample {
	
	private static int objName;	
	
	private String uuid(){
		objName = this.hashCode();
		return UUID.randomUUID().toString();
	}
	
	public void destroy() throws Exception {
		System.out.println("BeanExample destroy  running...");
	}
	
    public void init() {  
        System.out.println("BeanExample init-method is called:" + this.hashCode());  
        System.out.println("******************************");  
    }  
	
}
