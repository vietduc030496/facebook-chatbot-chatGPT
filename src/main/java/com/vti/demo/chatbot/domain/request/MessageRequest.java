package com.vti.demo.chatbot.domain.request;

import lombok.Data;

@Data
public class MessageRequest {
	
	private String role = "user";
	
	private String content;

}
