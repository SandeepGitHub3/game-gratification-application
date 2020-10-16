package com.sherlock.game.gameeventproducer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class GameProgressEvent {
	private Long playerId;
	private String gameId;
	private Long level;
	private UserRole userRole;
	private long timeSpent;
	private Double completionPercent;
	private Long createdAt;
}