package com.vti.demo.chatbot.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.vti.demo.chatbot.domain.request.ChatRequest;
import com.vti.demo.chatbot.domain.request.MessageRequest;
import com.vti.demo.chatbot.domain.request.UserRequest;
import com.vti.demo.chatbot.domain.response.ChatResponse;

@RestController
@RequestMapping("/chat")
public class ChatController {
	
	@PostMapping
	public ResponseEntity<?> chat(@RequestBody UserRequest questions) {
		String fooResourceUrl
		  = "";
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    headers.setBearerAuth("");
	    
	    ChatRequest chatRequest = new ChatRequest();
	    MessageRequest messageRequest = new MessageRequest();
	    messageRequest.setContent(questions.getQuestion());
	    chatRequest.setMessages(List.of(messageRequest));
	    
		
		HttpEntity<ChatRequest> entity = new HttpEntity<ChatRequest>(chatRequest,headers);
	
//		ChatResponse exchange = restTemplate.exchange(fooResourceUrl, HttpMethod.POST, entity, ChatResponse.class).getBody();
		String exchange = restTemplate.exchange(fooResourceUrl, HttpMethod.POST, entity, String.class).getBody();
		
//		Gson g = new Gson();  
//		ChatResponse s = g.fromJson(exchange, ChatResponse.class) ;
		
		return null;
	}

}
