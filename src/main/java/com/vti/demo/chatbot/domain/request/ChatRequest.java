package com.vti.demo.chatbot.domain.request;

import java.util.List;

import lombok.Data;

@Data
public class ChatRequest {

	private String model;

	private List<MessageRequest> messages;

}
