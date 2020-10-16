package com.sherlock.game.gameeventproducer.config;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface GameProgressEventBindings {

	@Output("game-progress-event-out")
	MessageChannel gameProgressEventOut();
}
