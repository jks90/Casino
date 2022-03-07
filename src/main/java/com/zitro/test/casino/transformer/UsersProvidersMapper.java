package com.zitro.test.casino.transformer;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.zitro.test.casino.models.UsersProviders;
import com.zitro.test.game.service.dto.UsersProvidersDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsersProvidersMapper {
	
	UsersProvidersMapper INSTANCE =  Mappers.getMapper(UsersProvidersMapper.class);
	
	UsersProvidersDto usersProvidersToUsersProvidersDto(UsersProviders entity);
	
	UsersProviders usersProvidersDtotoUsersProviders(UsersProvidersDto dto);

}
