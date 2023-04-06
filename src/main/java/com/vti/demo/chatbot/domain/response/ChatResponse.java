package com.vti.demo.chatbot.domain.response;

import java.util.List;

import lombok.Data;

@Data
public class ChatResponse {

	private String id;
	
	private String object;
	
	private long created;
	
	private String model;
	
	private UsageResponse usage;
	
	private List<Choice> choices;

}
