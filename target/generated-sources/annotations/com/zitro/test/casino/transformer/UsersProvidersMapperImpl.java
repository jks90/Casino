package com.zitro.test.casino.transformer;

import com.zitro.test.casino.models.UsersProviders;
import com.zitro.test.casino.models.UsersProviders.UsersProvidersBuilder;
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
public class UsersProvidersMapperImpl implements UsersProvidersMapper {

    @Override
    public UsersProvidersDto usersProvidersToUsersProvidersDto(UsersProviders entity) {
        if ( entity == null ) {
            return null;
        }

        UsersProvidersDtoBuilder usersProvidersDto = UsersProvidersDto.builder();

        usersProvidersDto.id( entity.getId() );
        usersProvidersDto.name( entity.getName() );

        return usersProvidersDto.build();
    }

    @Override
    public UsersProviders usersProvidersDtotoUsersProviders(UsersProvidersDto dto) {
        if ( dto == null ) {
            return null;
        }

        UsersProvidersBuilder usersProviders = UsersProviders.builder();

        usersProviders.id( dto.getId() );
        usersProviders.name( dto.getName() );

        return usersProviders.build();
    }
}
