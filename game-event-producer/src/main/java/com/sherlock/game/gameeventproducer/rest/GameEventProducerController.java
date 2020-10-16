package com.sherlock.game.gameeventproducer.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sherlock.game.gameeventproducer.producer.GameProgressEventProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/game-event-producer")
public class GameEventProducerController {

	private final GameProgressEventProducer gameProgressEventProducer;
	private final ObjectMapper objectMapper = new ObjectMapper();

	@GetMapping("/generate-event")
	@ResponseStatus(HttpStatus.OK)
	public void generateGameProgressEvents() {
		gameProgressEventProducer.generateGameProgressRandomEvent(100);
	}
}
