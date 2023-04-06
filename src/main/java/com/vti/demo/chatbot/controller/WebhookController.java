package com.vti.demo.chatbot.controller;

import static com.github.messenger4j.Messenger.CHALLENGE_REQUEST_PARAM_NAME;
import static com.github.messenger4j.Messenger.MODE_REQUEST_PARAM_NAME;
import static com.github.messenger4j.Messenger.SIGNATURE_HEADER_NAME;
import static com.github.messenger4j.Messenger.VERIFY_TOKEN_REQUEST_PARAM_NAME;
import static java.util.Optional.empty;
import static java.util.Optional.of;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.messenger4j.Messenger;
import com.github.messenger4j.exception.MessengerApiException;
import com.github.messenger4j.exception.MessengerIOException;
import com.github.messenger4j.exception.MessengerVerificationException;
import com.github.messenger4j.send.MessagePayload;
import com.github.messenger4j.send.MessagingType;
import com.github.messenger4j.send.NotificationType;
import com.github.messenger4j.send.message.TextMessage;
import com.github.messenger4j.send.recipient.IdRecipient;
import com.github.messenger4j.webhook.event.TextMessageEvent;
import com.vti.demo.chatbot.domain.response.ChatResponse;
import com.vti.demo.chatbot.service.GPTService;

@CrossOrigin("*")
@RestController
@RequestMapping("/webhook")
public class WebhookController {

	@Autowired
	private Messenger messenger;

	@Autowired
	private GPTService gptService;

	@GetMapping
	public ResponseEntity<?> verifyWebhook(@RequestParam(MODE_REQUEST_PARAM_NAME) final String mode,
			@RequestParam(VERIFY_TOKEN_REQUEST_PARAM_NAME) final String verifyToken,
			@RequestParam(CHALLENGE_REQUEST_PARAM_NAME) final String challenge) {
		try {
			messenger.verifyWebhook(mode, verifyToken);
			return ResponseEntity.ok(challenge);
		} catch (MessengerVerificationException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
		}
	}

	@PostMapping
	public ResponseEntity<Void> handleCallback(@RequestBody final String payload,
			@RequestHeader(SIGNATURE_HEADER_NAME) final String signature) throws MessengerVerificationException {
		this.messenger.onReceiveEvents(payload, of(signature), event -> {
			if (event.isTextMessageEvent()) {
				try {
					handleTextMessageEvent(event.asTextMessageEvent());
				} catch (MessengerApiException e) {
					e.printStackTrace();
				} catch (MessengerIOException e) {
					e.printStackTrace();
				}
			} else {
				String senderId = event.senderId();
				sendTextMessageUser(senderId, "Tôi là bot chỉ có thể xử lý tin nhắn văn bản.");
			}
		});
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	private void handleTextMessageEvent(TextMessageEvent event) throws MessengerApiException, MessengerIOException {
		ChatResponse response = gptService.chat(event.text());
		final String senderId = event.senderId();
		sendTextMessageUser(senderId, response.getChoices().get(0).getMessage().getContent());
	}

	/**
	 * 
	 * Send text message to idSender
	 * 
	 * @param idSender
	 * @param text
	 */
	private void sendTextMessageUser(String idSender, String text) {
		try {
			final IdRecipient recipient = IdRecipient.create(idSender);
			final NotificationType notificationType = NotificationType.REGULAR;
			final String metadata = "DEVELOPER_DEFINED_METADATA";

			final TextMessage textMessage = TextMessage.create(text, empty(), of(metadata));
			final MessagePayload messagePayload = MessagePayload.create(recipient, MessagingType.RESPONSE, textMessage,
					of(notificationType), empty());
			this.messenger.send(messagePayload);
		} catch (MessengerApiException | MessengerIOException e) {
			e.printStackTrace();
		}
	}

}
