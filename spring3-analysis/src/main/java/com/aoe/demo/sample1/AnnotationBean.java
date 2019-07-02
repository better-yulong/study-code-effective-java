package com.aoe.demo.sample1;


import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class AnnotationBean implements InitializingBean,DisposableBean{
	
	private String name ;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//实现InitializingBean接口
	public void afterPropertiesSet() throws Exception {
		System.out.println("AnnotationBean afterPropertiesSet has been created:" + this.hashCode());  
	}

	//实现DisposableBean接口
	public void destroy() throws Exception {
		System.out.println("AnnotationBean destroy  running...");
	}
	
}
