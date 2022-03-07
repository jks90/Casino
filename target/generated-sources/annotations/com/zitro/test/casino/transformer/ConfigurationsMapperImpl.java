package com.zitro.test.casino.transformer;

import com.zitro.test.casino.models.Configurations;
import com.zitro.test.casino.models.Configurations.ConfigurationsBuilder;
import com.zitro.test.casino.models.Games;
import com.zitro.test.casino.models.Games.GamesBuilder;
import com.zitro.test.game.service.dto.ConfigurationsDto;
import com.zitro.test.game.service.dto.ConfigurationsDto.ConfigurationsDtoBuilder;
import com.zitro.test.game.service.dto.GamesDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-07T18:42:27+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class ConfigurationsMapperImpl implements ConfigurationsMapper {

    @Override
    public ConfigurationsDto configurationsToConfigurationsDto(Configurations entity) {
        if ( entity == null ) {
            return null;
        }

        ConfigurationsDtoBuilder configurationsDto = ConfigurationsDto.builder();

        configurationsDto.id( entity.getId() );
        configurationsDto.gameType( gamesToGamesDto( entity.getGameType() ) );
        configurationsDto.provability( entity.getProvability() );
        configurationsDto.minBet( entity.getMinBet() );
        configurationsDto.maxBet( entity.getMaxBet() );
        configurationsDto.prize( entity.getPrize() );
        configurationsDto.costTime( entity.getCostTime() );

        return configurationsDto.build();
    }

    @Override
    public Configurations configurationsDtotoConfigurations(ConfigurationsDto dto) {
        if ( dto == null ) {
            return null;
        }

        ConfigurationsBuilder configurations = Configurations.builder();

        configurations.id( dto.getId() );
        configurations.gameType( gamesDtoToGames( dto.getGameType() ) );
        configurations.provability( dto.getProvability() );
        configurations.minBet( dto.getMinBet() );
        configurations.maxBet( dto.getMaxBet() );
        configurations.prize( dto.getPrize() );
        configurations.costTime( dto.getCostTime() );

        return configurations.build();
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

    protected Games gamesDtoToGames(GamesDto gamesDto) {
        if ( gamesDto == null ) {
            return null;
        }

        GamesBuilder games = Games.builder();

        games.id( gamesDto.getId() );
        games.name( gamesDto.getName() );

        return games.build();
    }
}
