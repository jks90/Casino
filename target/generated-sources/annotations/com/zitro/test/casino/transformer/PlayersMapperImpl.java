package com.zitro.test.casino.transformer;

import com.zitro.test.casino.models.PlayerConfig;
import com.zitro.test.casino.models.PlayerConfig.PlayerConfigBuilder;
import com.zitro.test.casino.models.Players;
import com.zitro.test.casino.models.UsersProviders;
import com.zitro.test.casino.models.UsersProviders.UsersProvidersBuilder;
import com.zitro.test.game.service.dto.PlayerConfigDto;
import com.zitro.test.game.service.dto.PlayersDto;
import com.zitro.test.game.service.dto.PlayersDto.PlayersDtoBuilder;
import com.zitro.test.game.service.dto.UsersProvidersDto;
import com.zitro.test.game.service.dto.UsersProvidersDto.UsersProvidersDtoBuilder;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-07T15:27:20+0100",
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
        playersDto.playerConfig( playerConfigSetToPlayerConfigDtoSet( entity.getPlayerConfig() ) );

        return playersDto.build();
    }

    @Override
    public Players playersDtotoPlayers(PlayersDto dto) {
        if ( dto == null ) {
            return null;
        }

        Players players = new Players();

        players.setId( dto.getId() );
        players.setBalance( dto.getBalance() );
        players.setTimeLimit( dto.getTimeLimit() );
        players.setTimePlaying( dto.getTimePlaying() );
        players.setUserProvider( usersProvidersDtoToUsersProviders( dto.getUserProvider() ) );
        players.setPlayerConfig( playerConfigDtoSetToPlayerConfigSet( dto.getPlayerConfig() ) );

        return players;
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

    protected PlayerConfigDto playerConfigToPlayerConfigDto(PlayerConfig playerConfig) {
        if ( playerConfig == null ) {
            return null;
        }

        PlayerConfigDto playerConfigDto = new PlayerConfigDto();

        playerConfigDto.setId( playerConfig.getId() );
        playerConfigDto.setPlayer( playerConfig.getPlayer() );
        playerConfigDto.setConfigurations( playerConfig.getConfigurations() );
        playerConfigDto.setTimeCreate( playerConfig.getTimeCreate() );
        playerConfigDto.setStatus( playerConfig.getStatus() );

        return playerConfigDto;
    }

    protected Set<PlayerConfigDto> playerConfigSetToPlayerConfigDtoSet(Set<PlayerConfig> set) {
        if ( set == null ) {
            return null;
        }

        Set<PlayerConfigDto> set1 = new HashSet<PlayerConfigDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( PlayerConfig playerConfig : set ) {
            set1.add( playerConfigToPlayerConfigDto( playerConfig ) );
        }

        return set1;
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

    protected PlayerConfig playerConfigDtoToPlayerConfig(PlayerConfigDto playerConfigDto) {
        if ( playerConfigDto == null ) {
            return null;
        }

        PlayerConfigBuilder playerConfig = PlayerConfig.builder();

        playerConfig.id( playerConfigDto.getId() );
        playerConfig.player( playerConfigDto.getPlayer() );
        playerConfig.configurations( playerConfigDto.getConfigurations() );
        playerConfig.timeCreate( playerConfigDto.getTimeCreate() );
        playerConfig.status( playerConfigDto.getStatus() );

        return playerConfig.build();
    }

    protected Set<PlayerConfig> playerConfigDtoSetToPlayerConfigSet(Set<PlayerConfigDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<PlayerConfig> set1 = new HashSet<PlayerConfig>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( PlayerConfigDto playerConfigDto : set ) {
            set1.add( playerConfigDtoToPlayerConfig( playerConfigDto ) );
        }

        return set1;
    }
}
