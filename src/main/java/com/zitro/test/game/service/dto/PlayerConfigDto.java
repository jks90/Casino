package com.zitro.test.game.service.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import com.zitro.test.casino.models.Configurations;
import com.zitro.test.casino.models.Players;
import com.zitro.test.types.RoundStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerConfigDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private UUID id;
	
	private Players player;
	
	private Configurations configurations;
	
	private Date timeCreate;
	
	private RoundStatus status;

}
