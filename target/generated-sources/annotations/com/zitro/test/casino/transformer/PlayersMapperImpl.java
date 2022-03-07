package com.zitro.test.casino.transformer;

import com.zitro.test.casino.models.Players;
import com.zitro.test.casino.models.Players.PlayersBuilder;
import com.zitro.test.casino.models.UsersProviders;
import com.zitro.test.casino.models.UsersProviders.UsersProvidersBuilder;
import com.zitro.test.game.service.dto.PlayersDto;
import com.zitro.test.game.service.dto.PlayersDto.PlayersDtoBuilder;
import com.zitro.test.game.service.dto.UsersProvidersDto;
import com.zitro.test.game.service.dto.UsersProvidersDto.UsersProvidersDtoBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-07T18:42:27+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class PlayersMapperImpl implements PlayersMapper {

    @Override
    public PlayersDto playersToPlayersDto(Players entity) {
        if ( entity == null ) {
            return null;
        }

        PlayersDtoBuilder playersDto = PlayersDto.builder();

        playersDto.id( entity.getId() );
        playersDto.balance( entity.getBalance() );
        playersDto.timeLimit( entity.getTimeLimit() );
        playersDto.timePlaying( entity.getTimePlaying() );
        playersDto.userProvider( usersProvidersToUsersProvidersDto( entity.getUserProvider() ) );

        return playersDto.build();
    }

    @Override
    public Players playersDtotoPlayers(PlayersDto dto) {
        if ( dto == null ) {
            return null;
        }

        PlayersBuilder players = Players.builder();

        players.id( dto.getId() );
        players.balance( dto.getBalance() );
        players.timeLimit( dto.getTimeLimit() );
        players.timePlaying( dto.getTimePlaying() );
        players.userProvider( usersProvidersDtoToUsersProviders( dto.getUserProvider() ) );

        return players.build();
    }

    protected UsersProvidersDto usersProvidersToUsersProvidersDto(UsersProviders usersProviders) {
        if ( usersProviders == null ) {
            return null;
        }

        UsersProvidersDtoBuilder usersProvidersDto = UsersProvidersDto.builder();

        usersProvidersDto.id( usersProviders.getId() );
        usersProvidersDto.name( usersProviders.getName() );

        return usersProvidersDto.build();
    }

    protected UsersProviders usersProvidersDtoToUsersProviders(UsersProvidersDto usersProvidersDto) {
        if ( usersProvidersDto == null ) {
            return null;
        }

        UsersProvidersBuilder usersProviders = UsersProviders.builder();

        usersProviders.id( usersProvidersDto.getId() );
        usersProviders.name( usersProvidersDto.getName() );

        return usersProviders.build();
    }
}
