package com.sherlock.game.gameeventprocessor.processor;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.processor.AbstractProcessor;

@Slf4j
public class PlayerTimeSpentWatchEventProcessor extends AbstractProcessor<String, Long> {

	@Override
	public void process(String key, Long totalTime) {
		String[] gameUserKeyTokens = key.split("-");
		String gameId = gameUserKeyTokens[0];
		Long playerId = Long.valueOf(gameUserKeyTokens[1]);
		log.info("Game :{} Player :{} Total Time Spent :{}", gameId,playerId,totalTime);
		//Players score may not be exactly 1000 ,so checking the range.
		//Once the totaltime is above 1010 we do not print anything.
		// We could define other levels as well based on actual business case.
		if (totalTime>3000 && totalTime<3500){
			log.info("Game:{} Player :{} ******** CONGRATULATIONS on completing 1000 mins",gameId,playerId);
		}
	}
}
