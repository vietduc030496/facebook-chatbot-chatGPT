package com.vti.demo.chatbot.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.lang.Nullable;
import org.springframework.web.client.RestTemplate;

public class HttpUtils {
	
	private static RestTemplate restTemplate;
	
	public static void addTemplate(RestTemplate injectTemplate) {
		restTemplate = injectTemplate;
	}
	
	public static <T> T post(String url, @Nullable HttpEntity<?> requestEntity, Class<T> responseType) {
		try {
			return restTemplate.exchange(url, HttpMethod.POST, requestEntity, responseType).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
