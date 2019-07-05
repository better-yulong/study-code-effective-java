package com.aoe.demo.aop;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

@Repository
public class BizAspectjExample implements BizAspectjExampleInterf {
	
	/* (non-Javadoc)
	 * @see com.aoe.demo.aop.BizAspectjExampleInterf#init()
	 */
	@PostConstruct
	public void init(){
		System.out.println("BizAspectjExample init...");
	}
	
	public void biz(){
		System.out.println("BizAspectjExample biz...");
	}
}
