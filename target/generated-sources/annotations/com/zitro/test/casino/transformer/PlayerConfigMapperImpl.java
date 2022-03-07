package com.zitro.test.casino.transformer;

import com.zitro.test.casino.models.PlayerConfig;
import com.zitro.test.casino.models.PlayerConfig.PlayerConfigBuilder;
import com.zitro.test.game.service.dto.PlayerConfigDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-07T15:27:20+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class PlayerConfigMapperImpl implements PlayerConfigMapper {

    @Override
    public PlayerConfigDto PlayerConfigToPlayerConfigDto(PlayerConfig entity) {
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
    public PlayerConfig playerConfigDtotoPlayerConfig(PlayerConfigDto dto) {
        if ( dto == null ) {
            return null;
        }

        PlayerConfigBuilder playerConfig = PlayerConfig.builder();

        playerConfig.id( dto.getId() );
        playerConfig.player( dto.getPlayer() );
        playerConfig.configurations( dto.getConfigurations() );
        playerConfig.timeCreate( dto.getTimeCreate() );
        playerConfig.status( dto.getStatus() );

        return playerConfig.build();
    }
}
