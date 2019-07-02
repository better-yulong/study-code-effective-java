package com.aoe.demo.sample1;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnnotationBeanInjectExample implements InitializingBean,DisposableBean{
	
	@Resource
	private BeanExample beanExample;
	
	@Autowired
	private BeanExample beanExample1;
	
	@Autowired
	private BeanExample beanExample2;
	
	@Autowired
	private AnnotationBean annotationBean;
	
	private String name ;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//实现InitializingBean接口
	public void afterPropertiesSet() throws Exception {
		System.out.println("beanExample:" + beanExample.hashCode());
		System.out.println("beanExample1:" + beanExample1.hashCode());
		System.out.println("beanExample2:" + beanExample2.hashCode());
		System.out.println("annotationBean:" + annotationBean.hashCode());
		System.out.println("AnnotationBeanInjectExample afterPropertiesSet has been created:" + this.hashCode());  
	}

	//实现DisposableBean接口
	public void destroy() throws Exception {
		System.out.println("AnnotationBeanInjectExample destroy  running...");
	}
	
	@PostConstruct
    public void init() {  
        System.out.println("AnnotationBeanInjectExample init-method is called:" + this.hashCode());  
        System.out.println("******************************");  
    }  
	
	@PreDestroy 
	public void preDestroy() throws Exception {
		System.out.println("AnnotationBeanInjectExample preDestroy running...");
	}
}
