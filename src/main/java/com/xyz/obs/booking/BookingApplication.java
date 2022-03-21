package com.xyz.obs.booking;

import com.xyz.obs.booking.listener.PaymentListener;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookingApplication {
	public final static String PAYMENT_MESSAGE_QUEUE = "payment-message-queue";

	@Bean
	Queue queue() {
		return new Queue(PAYMENT_MESSAGE_QUEUE, false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange("xyz-obs-exchange");
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(PAYMENT_MESSAGE_QUEUE);
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
											 MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(PAYMENT_MESSAGE_QUEUE);
		container.setMessageListener(listenerAdapter);
		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(PaymentListener receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}


	public static void main(String[] args) {
		SpringApplication.run(BookingApplication.class, args);
	}

}
