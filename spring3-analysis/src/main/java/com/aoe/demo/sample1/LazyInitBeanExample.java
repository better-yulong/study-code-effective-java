package com.aoe.demo.sample1;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Repository
@Lazy
public class LazyInitBeanExample {
	
	@PostConstruct
	public void init(){
		System.out.println("LazyInitBeanExample init...");
	}
}
