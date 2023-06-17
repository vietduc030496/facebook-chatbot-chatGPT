package com.vti.demo.chatbot.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.vti.demo.chatbot.config.ChatGPTComponent;
import com.vti.demo.chatbot.domain.request.ChatRequest;
import com.vti.demo.chatbot.domain.request.MessageRequest;
import com.vti.demo.chatbot.domain.response.ChatResponse;
import com.vti.demo.chatbot.util.HttpUtils;

@Service
public class GPTChatService {

	@Autowired
	private ChatGPTComponent chatGPTComponent;

	/**
	 * Send content to chatGPT
	 * 
	 * @param content user content
	 * @return chatGPT response
	 */
	public ChatResponse chat(String content) {
		String baseUrl = chatGPTComponent.getBaseUrl();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setBearerAuth(chatGPTComponent.getAccessToken());

		ChatRequest request = new ChatRequest();
		request.setModel(chatGPTComponent.getModel());
		request.setMessages(List.of(new MessageRequest(content)));

		HttpEntity<ChatRequest> requestEntity = new HttpEntity<>(request, headers);
		return HttpUtils.post(baseUrl, requestEntity, ChatResponse.class);
	}
}
