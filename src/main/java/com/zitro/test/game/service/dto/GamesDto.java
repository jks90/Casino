package com.zitro.test.game.service.dto;

import java.io.Serializable;
import java.util.UUID;

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
public class GamesDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private UUID id;
	private String name;
}
