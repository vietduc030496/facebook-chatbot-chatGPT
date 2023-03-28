package com.vti.demo.chatbot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ChatGPTComponent {

	@Value("${chatGPT.base_url}")
	private String baseUrl;
	
	@Value("${chatGPT.access_token}")
	private String accessToken;
}
