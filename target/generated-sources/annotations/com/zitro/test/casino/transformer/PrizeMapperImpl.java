package com.zitro.test.casino.transformer;

import com.zitro.test.casino.models.Prize;
import com.zitro.test.casino.models.Prize.PrizeBuilder;
import com.zitro.test.game.service.dto.PrizeDto;
import com.zitro.test.game.service.dto.PrizeDto.PrizeDtoBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-07T15:27:20+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class PrizeMapperImpl implements PrizeMapper {

    @Override
    public PrizeDto prizeToPrizeDto(Prize entity) {
        if ( entity == null ) {
            return null;
        }

        PrizeDtoBuilder prizeDto = PrizeDto.builder();

        prizeDto.prize( entity.getPrize() );
        prizeDto.name( entity.getName() );

        return prizeDto.build();
    }

    @Override
    public Prize prizeDtotoPrize(PrizeDto dto) {
        if ( dto == null ) {
            return null;
        }

        PrizeBuilder prize = Prize.builder();

        prize.prize( dto.getPrize() );
        prize.name( dto.getName() );

        return prize.build();
    }

    @Override
    public List<PrizeDto> prizeListToPrizeDtoList(List<Prize> listdto) {
        if ( listdto == null ) {
            return null;
        }

        List<PrizeDto> list = new ArrayList<PrizeDto>( listdto.size() );
        for ( Prize prize : listdto ) {
            list.add( prizeToPrizeDto( prize ) );
        }

        return list;
    }

    @Override
    public List<Prize> prizeDtoListToPrizeList(List<PrizeDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Prize> list1 = new ArrayList<Prize>( list.size() );
        for ( PrizeDto prizeDto : list ) {
            list1.add( prizeDtotoPrize( prizeDto ) );
        }

        return list1;
    }
}
