package com.sherlock.game.gameeventprocessor;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface GameProgressEventProcessorBindings {

	public static final String GAME_PROGRESS_EVENT_IN="game-progress-event-in";
	@Input(GAME_PROGRESS_EVENT_IN)
	SubscribableChannel gameProgressEventIn();
}
