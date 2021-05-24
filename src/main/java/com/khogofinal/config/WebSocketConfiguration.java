package com.khogofinal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer{
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/ws") // usado pelo cliente para se conectar ao servidor STOMP
		        .setAllowedOrigins("*") // setAllowedOrigins("mydomain.com").withSockJS(); http://localhost:4200
		        .withSockJS();
	}
	
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/user", "/topic", "/queue"); // destino das mensagens
    	registry.setApplicationDestinationPrefixes("/app");//prefixo "/app" para mensagens que são vinculadas a métodos anotados com @MessageMapping
    	//registry.setUserDestinationPrefix("/user");
    	//registry.setApplicationDestinationPrefixes("/app");
    	
    	
    	//O prefixo de destino do usuário "/user" é usado pelo ConvertAndSendToUser método de 
    	//SimpleMessagingTemplate para prefixar todos os destinos específicos do usuário com /user.
	}

}
