package com.vti.demo.chatbot.domain.request;

import java.util.List;

import lombok.Data;

@Data
public class ChatRequest {
	
	private String model = "gpt-3.5-turbo";
	
	private List<MessageRequest> messages;

	
}
