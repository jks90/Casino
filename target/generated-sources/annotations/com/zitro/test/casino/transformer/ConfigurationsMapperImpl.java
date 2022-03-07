package com.zitro.test.casino.transformer;

import com.zitro.test.casino.models.Configurations;
import com.zitro.test.casino.models.Games;
import com.zitro.test.casino.models.Games.GamesBuilder;
import com.zitro.test.casino.models.PlayerConfig;
import com.zitro.test.casino.models.PlayerConfig.PlayerConfigBuilder;
import com.zitro.test.game.service.dto.ConfigurationsDto;
import com.zitro.test.game.service.dto.GamesDto;
import com.zitro.test.game.service.dto.PlayerConfigDto;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-07T15:27:20+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class ConfigurationsMapperImpl implements ConfigurationsMapper {

    @Override
    public ConfigurationsDto configurationsToConfigurationsDto(Configurations entity) {
        if ( entity == null ) {
            return null;
        }

        ConfigurationsDto configurationsDto = new ConfigurationsDto();

        configurationsDto.setId( entity.getId() );
        configurationsDto.setGameType( gamesToGamesDto( entity.getGameType() ) );
        configurationsDto.setProvability( entity.getProvability() );
        configurationsDto.setMinBet( entity.getMinBet() );
        configurationsDto.setMaxBet( entity.getMaxBet() );
        configurationsDto.setPrize( entity.getPrize() );
        configurationsDto.setCostTime( entity.getCostTime() );
        configurationsDto.setPlayerConfig( playerConfigSetToPlayerConfigDtoSet( entity.getPlayerConfig() ) );

        return configurationsDto;
    }

    @Override
    public Configurations configurationsDtotoConfigurations(ConfigurationsDto dto) {
        if ( dto == null ) {
            return null;
        }

        Configurations configurations = new Configurations();

        configurations.setId( dto.getId() );
        configurations.setGameType( gamesDtoToGames( dto.getGameType() ) );
        configurations.setProvability( dto.getProvability() );
        configurations.setMinBet( dto.getMinBet() );
        configurations.setMaxBet( dto.getMaxBet() );
        configurations.setPrize( dto.getPrize() );
        configurations.setCostTime( dto.getCostTime() );
        configurations.setPlayerConfig( playerConfigDtoSetToPlayerConfigSet( dto.getPlayerConfig() ) );

        return configurations;
    }

    @Override
    public List<ConfigurationsDto> configurationsListToConfigurationsDtoList(List<Configurations> listdto) {
        if ( listdto == null ) {
            return null;
        }

        List<ConfigurationsDto> list = new ArrayList<ConfigurationsDto>( listdto.size() );
        for ( Configurations configurations : listdto ) {
            list.add( configurationsToConfigurationsDto( configurations ) );
        }

        return list;
    }

    @Override
    public List<Configurations> configurationsDtoListToConfigurationsList(List<ConfigurationsDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Configurations> list1 = new ArrayList<Configurations>( list.size() );
        for ( ConfigurationsDto configurationsDto : list ) {
            list1.add( configurationsDtotoConfigurations( configurationsDto ) );
        }

        return list1;
    }

    protected GamesDto gamesToGamesDto(Games games) {
        if ( games == null ) {
            return null;
        }

        GamesDto gamesDto = new GamesDto();

        gamesDto.setId( games.getId() );
        gamesDto.setName( games.getName() );

        return gamesDto;
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

    protected Games gamesDtoToGames(GamesDto gamesDto) {
        if ( gamesDto == null ) {
            return null;
        }

        GamesBuilder games = Games.builder();

        games.id( gamesDto.getId() );
        games.name( gamesDto.getName() );

        return games.build();
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
