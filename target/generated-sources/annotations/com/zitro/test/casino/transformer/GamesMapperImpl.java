package com.zitro.test.casino.transformer;

import com.zitro.test.casino.models.Games;
import com.zitro.test.casino.models.Games.GamesBuilder;
import com.zitro.test.game.service.dto.GamesDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-07T15:27:20+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class GamesMapperImpl implements GamesMapper {

    @Override
    public GamesDto gamesToGamesDto(Games entity) {
        if ( entity == null ) {
            return null;
        }

        GamesDto gamesDto = new GamesDto();

        gamesDto.setId( entity.getId() );
        gamesDto.setName( entity.getName() );

        return gamesDto;
    }

    @Override
    public Games gamesDtotoGames(GamesDto dto) {
        if ( dto == null ) {
            return null;
        }

        GamesBuilder games = Games.builder();

        games.id( dto.getId() );
        games.name( dto.getName() );

        return games.build();
    }
}
