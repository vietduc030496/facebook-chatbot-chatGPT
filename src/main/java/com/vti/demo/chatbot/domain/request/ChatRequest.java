package com.vti.demo.chatbot.domain.request;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import lombok.Data;

@Data
public class ChatRequest {

	@Value("{chatgpt.model}")
	private String model;

	private List<MessageRequest> messages;

}
