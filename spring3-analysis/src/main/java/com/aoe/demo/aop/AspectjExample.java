package com.aoe.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;

//非注解的切面类，采用xml配置
public class AspectjExample {
	
	public void before(){
		System.out.println("AspectjExample before...");
	}
	
	public void after(){
		System.out.println("AspectjExample after...");
	}
	public void around(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("AspectjExample around before...");
		pjp.proceed();
		System.out.println("AspectjExample around after...");
	}

}
