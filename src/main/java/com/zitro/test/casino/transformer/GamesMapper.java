package com.zitro.test.casino.transformer;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.zitro.test.casino.models.Games;
import com.zitro.test.game.service.dto.GamesDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GamesMapper {
	
	GamesMapper INSTANCE =  Mappers.getMapper(GamesMapper.class);
	
	GamesDto gamesToGamesDto(Games entity);
	
	Games gamesDtotoGames(GamesDto dto);

}
