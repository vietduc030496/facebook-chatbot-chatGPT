package com.vti.demo.chatbot.domain.request;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MessageRequest {
	
	private String role = "user";
	
	@NonNull
	private String content;

}
