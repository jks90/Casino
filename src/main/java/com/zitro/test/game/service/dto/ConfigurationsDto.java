package com.zitro.test.game.service.dto;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConfigurationsDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID id;

	private GamesDto gameType;

	private Double provability;

	private Double minBet;

	private Double maxBet;

	private Double prize;
	
	private Long costTime;
	
	@JsonIgnore
	Set<PlayerConfigDto> playerConfig;

}
