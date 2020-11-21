package com.sherlock.game.gameeventprocessor;

import com.sherlock.game.gameeventprocessor.model.GameProgressEvent;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface GameProgressEventProcessorBindings {

	String GAME_PROGRESS_EVENT_IN = "game-progress-event-in";
	String GAME_PROGRESS_EVENT_STREAM_IN = "game-progress-event-stream-in";

	@Input(GAME_PROGRESS_EVENT_IN)
	SubscribableChannel gameProgressEventIn();

	@Input(GAME_PROGRESS_EVENT_STREAM_IN)
	KStream<String, GameProgressEvent> gameProgressEventStream();
}
