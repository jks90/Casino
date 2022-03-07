package com.zitro.test.casino.transformer;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.zitro.test.casino.models.Bet;
import com.zitro.test.game.service.dto.PlayerConfigDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PlayerConfigMapper {
	
	PlayerConfigMapper INSTANCE = Mappers.getMapper(PlayerConfigMapper.class);
	
	PlayerConfigDto PlayerConfigToPlayerConfigDto(Bet entity);

	Bet playerConfigDtotoPlayerConfig(PlayerConfigDto dto);

}
