package com.khogofinal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/localizacoes").permitAll()
				.antMatchers(HttpMethod.POST, "/usuarios/**").permitAll()
				.antMatchers("/usuarios/**").authenticated()
				.antMatchers("/serialNumber/**").permitAll()
				.antMatchers("/login/**").permitAll()
				.antMatchers("/ws/**").permitAll()
				.antMatchers("/telegrans/**").permitAll()
				.antMatchers("/clientes/**", 
							 "/veiculos/**",
							 "/enderecos/**").authenticated()
				.anyRequest().denyAll();	
	}
}
