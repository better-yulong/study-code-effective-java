package com.zyl.demo.server.mq;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@Component
public class Receiver {
	
	private final static String QUEUE_NAME = "spring-boot-queue1";
	
	private final static String EXCHANGE_NAME = "spring-boot-exchange";
		
	private final static String ROUTING_KEY = "spring-boot-rk1";
	
	@Autowired
	private AmqpAdmin amqpAdmin;
	
	@Autowired
	private Binding binding;
	
	@Autowired
	private  Queue queue;
	
	@Bean
    Queue queue() {
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NAME,false,false);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
    
    @Transactional
	@RabbitListener(queues = QUEUE_NAME )
	public void processMessage(String content) {
		//amqpAdmin.purgeQueue(QUEUE_NAME, false);
		amqpAdmin.declareQueue(queue);
		amqpAdmin.declareBinding(binding);
		System.out.println(content);
	}
	
}
