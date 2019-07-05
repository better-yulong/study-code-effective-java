package com.framework.aoe.beans.factory.support.process1;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class SimpleProcessorExample implements BeanPostProcessor, InitializingBean {

	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("SimpleProcessorExample postProcessBeforeInitialization。。。");
		return bean;
	}

	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("SimpleProcessorExample postProcessAfterInitialization。。。");
		return bean;
	}

	public void afterPropertiesSet() throws Exception {
		System.out.println("SimpleProcessorExample afterPropertiesSet。。。");
	}

}

