package com.zitro.test.casino.transformer;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.zitro.test.casino.models.Configurations;
import com.zitro.test.game.service.dto.ConfigurationsDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ConfigurationsMapper {

	ConfigurationsMapper INSTANCE = Mappers.getMapper(ConfigurationsMapper.class);

	ConfigurationsDto configurationsToConfigurationsDto(Configurations entity);

	Configurations configurationsDtotoConfigurations(ConfigurationsDto dto);
	
	List<ConfigurationsDto> configurationsListToConfigurationsDtoList(List<Configurations> listdto);
	
	List<Configurations> configurationsDtoListToConfigurationsList(List<ConfigurationsDto> list);

}
