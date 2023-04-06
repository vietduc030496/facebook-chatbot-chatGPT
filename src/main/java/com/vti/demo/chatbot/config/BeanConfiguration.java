package com.vti.demo.chatbot.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.github.messenger4j.Messenger;
import com.vti.demo.chatbot.util.HttpUtils;

@Configuration
public class BeanConfiguration {
	
	@Autowired
	private EnviromentComponent env;
	
	@Autowired
	private ChatGPTComponent chatComponent;
	
	@Bean
	public Messenger messager() {
		return Messenger.create(env.getPageAccessToken(), env.getAppSecret(), env.getVerifyToken());
	}
	
	@Bean
	public HttpHeaders headers() {
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    headers.setBearerAuth(chatComponent.getAccessToken());
	    return headers;
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		RestTemplate restTemplate = new RestTemplate();
		HttpUtils.addTemplate(restTemplate);
		return restTemplate;
	}

}
