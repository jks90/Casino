package com.zitro.test.casino.transformer;

import com.zitro.test.casino.models.Bet;
import com.zitro.test.casino.models.Bet.BetBuilder;
import com.zitro.test.game.service.dto.PlayerConfigDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-07T18:42:27+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class PlayerConfigMapperImpl implements PlayerConfigMapper {

    @Override
    public PlayerConfigDto PlayerConfigToPlayerConfigDto(Bet entity) {
        if ( entity == null ) {
            return null;
        }

        PlayerConfigDto playerConfigDto = new PlayerConfigDto();

        playerConfigDto.setId( entity.getId() );
        playerConfigDto.setPlayer( entity.getPlayer() );
        playerConfigDto.setConfigurations( entity.getConfigurations() );
        playerConfigDto.setTimeCreate( entity.getTimeCreate() );
        playerConfigDto.setStatus( entity.getStatus() );

        return playerConfigDto;
    }

    @Override
    public Bet playerConfigDtotoPlayerConfig(PlayerConfigDto dto) {
        if ( dto == null ) {
            return null;
        }

        BetBuilder bet = Bet.builder();

        bet.id( dto.getId() );
        bet.player( dto.getPlayer() );
        bet.configurations( dto.getConfigurations() );
        bet.timeCreate( dto.getTimeCreate() );
        bet.status( dto.getStatus() );

        return bet.build();
    }
}
