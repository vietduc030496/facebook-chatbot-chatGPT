package com.vti.demo.chatbot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@PropertySource(value = { "classpath:env.yaml" }, ignoreResourceNotFound = true)
@ConfigurationProperties(prefix = "chatgpt")
public class ChatGPTComponent {

	@Value("${chat_base_url}")
	private String chatBaseUrl;

	@Value("${image_base_url}")
	private String imageBaseUrl;

	@Value("${access_token}")
	private String accessToken;

	@Value("${model}")
	private String model;

	@Value("${mode}")
	private String mode;

	public String getBaseUrl() {
		return switch (mode) {
		case "chat" -> chatBaseUrl;
		case "image" -> imageBaseUrl;
		default -> chatBaseUrl;
		};
	}
}
