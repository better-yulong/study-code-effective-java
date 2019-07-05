package com.aoe.demo.sample1;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.aoe.demo.aop.BizAspectjExample;
import com.aoe.demo.aop.BizAspectjExampleInterf;

@Component
public class LazyInitTestLinstener implements ApplicationListener,ApplicationContextAware {
	
	@Autowired
	private ApplicationContext applicationContext;

	public void onApplicationEvent(ApplicationEvent event) {
		if(event instanceof ContextRefreshedEvent){
			System.out.println("LazyInitTestLinstener --> ContextRefreshedEvent");
			//LazyInitBeanExample 基于@Lazy注解使得在前面的bean初始化时并不会实例化，而里面待首次使用时才会实例化（但如果通过@Autowired、@Rresource也会在前面在注解处理时实例化。
			LazyInitBeanExample lazyInitBeanExample  = applicationContext.getBean(LazyInitBeanExample.class);
			System.out.println("lazyInitBeanExample:" + lazyInitBeanExample.hashCode());
			
			//基于类获取bean会无法获取
			//BizAspectjExample bizAspectjExample  = (BizAspectjExample) applicationContext.getBean(BizAspectjExample.class);
			//通过接口、beanName可以正常获取到bean
			BizAspectjExampleInterf bizAspectjExample  = (BizAspectjExampleInterf) applicationContext.getBean(BizAspectjExampleInterf.class);
			//BizAspectjExampleInterf bizAspectjExample  = (BizAspectjExampleInterf) applicationContext.getBean("bizAspectjExample");
			bizAspectjExample.biz();
		}
		
	}
	
	@PostConstruct
	public void init(){
		System.out.println("LazyInitTestLinstener ... ");
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext ;
	}

}
