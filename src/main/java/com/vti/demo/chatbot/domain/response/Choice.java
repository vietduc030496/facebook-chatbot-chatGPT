package com.vti.demo.chatbot.domain.response;

import lombok.Data;

@Data
public class Choice {

	private MessageResponse message;

	private String finish_reason;

	private int index;

}
