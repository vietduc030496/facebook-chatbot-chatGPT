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
	
	
	@Value("${app_secret}")
	private String appSecret;
	
	@Value("${verify_token}")
	private String verifyToken;
	
	@Value("${page_access_token}")
	private String pageAccessToken;
	

}
