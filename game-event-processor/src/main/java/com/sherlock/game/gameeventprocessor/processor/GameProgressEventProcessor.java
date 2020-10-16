package com.sherlock.game.gameeventprocessor.processor;

import com.sherlock.game.gameeventprocessor.GameProgressEventProcessorBindings;
import com.sherlock.game.gameeventprocessor.model.GameProgressEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableBinding(GameProgressEventProcessorBindings.class)
public class GameProgressEventProcessor {

	@StreamListener(GameProgressEventProcessorBindings.GAME_PROGRESS_EVENT_IN)
	public void processGameProgressEvent(GameProgressEvent gameProgressEvent) {
		log.info("Received Event:{}", gameProgressEvent);
	}
}
