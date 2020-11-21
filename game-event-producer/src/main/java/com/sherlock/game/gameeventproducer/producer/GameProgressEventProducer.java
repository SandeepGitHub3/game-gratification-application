package com.sherlock.game.gameeventproducer.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sherlock.game.gameeventproducer.config.GameProgressEventBindings;
import com.sherlock.game.gameeventproducer.model.GameProgressEvent;
import com.sherlock.game.gameeventproducer.model.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
@EnableBinding(GameProgressEventBindings.class)
public class GameProgressEventProducer {

	private static final DecimalFormat DECIMAL_FORMAT_2 = new DecimalFormat("#.##");
	private final GameProgressEventBindings gameProgressEventBindings;
	private final ObjectMapper objectMapper = new ObjectMapper();
	private final List<UserRole> userRoles = Arrays.asList(UserRole.ADMIN, UserRole.PLAYER);
	private final Random random = new Random();

	public void generateGameProgressRandomEvent(Integer noOfEvents) {
		//IntStream.range(0, noOfEvents).forEach(i -> sendMessage());

		/*Use this incase we need to generate a continous flow of progress event messages to simulate Production like scenario*/
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(this::sendMessage, 10, 300, TimeUnit.MILLISECONDS);
	}

	@SneakyThrows
	private void sendMessage() {

		GameProgressEvent gameProgressEvent = GameProgressEvent.builder()
				.playerId((long) (Math.random() * (10 - 1 + 1) + 1))
				.gameId("GAME".concat(String.valueOf((long) (Math.random() * (10 - 1 + 1) + 1))))
				.level((long) (Math.random() * (10 - 1 + 1) + 1))
				.userRole(userRoles.get(random.nextInt(userRoles.size())))
				.completionPercent(new BigDecimal(Math.random() * (100 - 1 + 1) + 1).setScale(2, RoundingMode.HALF_EVEN).doubleValue())
				.timeSpent((long) (Math.random() * (100 - 1 + 5) + 1))
				.createdAt(ZonedDateTime.now().toInstant().toEpochMilli())
				.build();

		Message<byte[]> gameProgressEventMessage = MessageBuilder
				.withPayload(objectMapper.writeValueAsString(gameProgressEvent).getBytes())
				.setHeader(KafkaHeaders.MESSAGE_KEY, gameProgressEvent.getPlayerId().toString().getBytes())
				.build();
		try {
			this.gameProgressEventBindings.gameProgressEventOut().send(gameProgressEventMessage);
			log.info("Sent {}", gameProgressEvent);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
}