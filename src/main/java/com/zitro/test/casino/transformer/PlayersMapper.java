package com.zitro.test.casino.transformer;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.zitro.test.casino.models.Players;
import com.zitro.test.game.service.dto.PlayersDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PlayersMapper {

	PlayersMapper INSTANCE = Mappers.getMapper(PlayersMapper.class);

	PlayersDto playersToPlayersDto(Players entity);

	Players playersDtotoPlayers(PlayersDto dto);

}
