package com.vti.demo.chatbot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.messenger4j.Messenger;

@Configuration
public class BeanConfiguration {
	
	@Autowired
	private EnviromentComponent env;
	
	@Bean
	public Messenger messager() {
		return Messenger.create(env.getPageAccessToken(), env.getAppSecret(), env.getVerifyToken());
	}

}
