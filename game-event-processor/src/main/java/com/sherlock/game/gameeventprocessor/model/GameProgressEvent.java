package com.sherlock.game.gameeventprocessor.model;

import com.sherlock.game.gameeventprocessor.UserRole;
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