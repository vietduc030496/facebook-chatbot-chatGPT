package com.vti.demo.chatbot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@PropertySource(value = {"classpath:env.yaml"}, ignoreResourceNotFound = true)
@ConfigurationProperties(prefix = "chatgpt")
public class ChatGPTComponent {

	@Value("${base_url}")
	private String baseUrl;
	
	@Value("${access_token}")
	private String accessToken;
}
