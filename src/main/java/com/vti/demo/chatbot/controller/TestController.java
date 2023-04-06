package com.vti.demo.chatbot.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class TestController {

	/**
	 * For test
	 * 
	 * @return hello world.
	 */
	@GetMapping("/test")
	public String test() {
		return "hello world.";
	}
}
