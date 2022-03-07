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
public class PlayersDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private UUID id;

	private Double balance;
	private Long timeLimit;
	private Long timePlaying;
	private UsersProvidersDto userProvider;
	
	@JsonIgnore
	private Set<PlayerConfigDto> playerConfig;

}
