package com.zitro.test.game.service.dto;

import java.io.Serializable;
import java.util.UUID;

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
public class UsersProvidersDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private UUID id;

	private String name;
}
