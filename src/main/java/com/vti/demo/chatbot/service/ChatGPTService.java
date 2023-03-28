package com.vti.demo.chatbot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.vti.demo.chatbot.config.ChatGPTComponent;
import com.vti.demo.chatbot.util.HttpUtils;

@Service
public class ChatGPTService {
	
	@Autowired
	private ChatGPTComponent chatGPTComponent;

	public <T> T chat(@Nullable HttpEntity<?> requestEntity, Class<T> responseType) {
		String baseUrl = chatGPTComponent.getBaseUrl();
		
		return HttpUtils.post(baseUrl, requestEntity, responseType);
	}
}
