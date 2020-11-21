package com.sherlock.game.gameeventprocessor.processor;

import com.sherlock.game.gameeventprocessor.GameProgressEventProcessorBindings;
import com.sherlock.game.gameeventprocessor.UserRole;
import com.sherlock.game.gameeventprocessor.model.GameProgressEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import static com.sherlock.game.gameeventprocessor.GameProgressEventProcessorBindings.GAME_PROGRESS_EVENT_STREAM_IN;
import static java.lang.String.format;

@Slf4j
@Component
@EnableBinding(GameProgressEventProcessorBindings.class)
public class GameProgressEventProcessor {

	@StreamListener(GameProgressEventProcessorBindings.GAME_PROGRESS_EVENT_IN)
	public void processGameProgressEvent(GameProgressEvent gameProgressEvent) {
		//log.info("Received Event:{}", gameProgressEvent);
	}

	@StreamListener
	public void playerTimeSpentWatchEventStreamProcessor(@Input(GAME_PROGRESS_EVENT_STREAM_IN)
			KStream<String, GameProgressEvent> gameProgressEventKStream) {

		gameProgressEventKStream
				.peek((user, gameProgressEvent) -> log.info("Consumed :{} {} ", user, gameProgressEvent))
				.filter((user, gameProgressEvent) -> UserRole.PLAYER.equals(gameProgressEvent.getUserRole()))
				.map((user, gameProgressEvent) ->
						new KeyValue<>(
								format("%s-%s", gameProgressEvent.getGameId(), gameProgressEvent.getPlayerId()),
								gameProgressEvent.getTimeSpent()))
				.groupByKey(Grouped.with(Serdes.String(), Serdes.Long()))
				.reduce(Long::sum)
				.toStream()
				.process(() -> new PlayerTimeSpentWatchEventProcessor());
	}
}
