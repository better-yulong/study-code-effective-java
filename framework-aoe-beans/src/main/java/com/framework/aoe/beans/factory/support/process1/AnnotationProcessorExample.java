package com.framework.aoe.beans.factory.support.process1;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class AnnotationProcessorExample implements BeanPostProcessor, InitializingBean {

	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("AnnotationProcessorExample postProcessBeforeInitialization。。。");
		return null;
	}

	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("AnnotationProcessorExample postProcessAfterInitialization。。。");
		return null;
	}

	public void afterPropertiesSet() throws Exception {
		System.out.println("AnnotationProcessorExample afterPropertiesSet。。。");
	}

}
