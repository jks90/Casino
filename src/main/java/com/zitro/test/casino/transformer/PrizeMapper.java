package com.zitro.test.casino.transformer;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.zitro.test.casino.models.Prize;
import com.zitro.test.game.service.dto.PrizeDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PrizeMapper {
	
	PrizeMapper INSTANCE = Mappers.getMapper(PrizeMapper.class);

	PrizeDto prizeToPrizeDto(Prize entity);

	Prize prizeDtotoPrize(PrizeDto dto);
	
	List<PrizeDto> prizeListToPrizeDtoList(List<Prize> listdto);
	
	List<Prize> prizeDtoListToPrizeList(List<PrizeDto> list);

}
