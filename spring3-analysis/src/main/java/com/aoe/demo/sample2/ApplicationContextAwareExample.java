package com.aoe.demo.sample2;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextAwareExample implements ApplicationContextAware {
	
	private String name ;
	
	private ApplicationContext applicationContext ;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext ;
	}
	
	@PostConstruct
	public void init(){
		System.out.println("ApplicationContextAwareExample  name:" + this.name);
		System.out.println("ApplicationContextAwareExample  DisplayName:" + this.applicationContext.getDisplayName());
		System.out.println("ApplicationContextAwareExample  StartupDate:" + this.applicationContext.getStartupDate());
	}

}
