package com.khogofinal.resources;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import com.khogofinal.domain.Greeting;
import com.khogofinal.domain.Logar;

@RestController
public class LogarResource {
	
	@MessageMapping("/register")
	@SendTo("/queue/greetings")
	public Greeting greeting (Logar obj) {
		System.out.println("Mensagem: "+ obj.getName());
		return new Greeting("Hello, " + HtmlUtils.htmlEscape(obj.getName()) + "!");
	}

}
