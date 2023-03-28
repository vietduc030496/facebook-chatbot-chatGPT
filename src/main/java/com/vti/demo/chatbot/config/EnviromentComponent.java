package com.vti.demo.chatbot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@PropertySource(value = {"classpath:env.yaml"}, ignoreResourceNotFound = true)
@ConfigurationProperties(prefix = "messenger4j")
@Data
public class EnviromentComponent {
	
	
	@Value("${appSecret}")
	private String appSecret;
	
	@Value("${verifyToken}")
	private String verifyToken;
	
	@Value("${pageAccessToken}")
	private String pageAccessToken;
	

}
