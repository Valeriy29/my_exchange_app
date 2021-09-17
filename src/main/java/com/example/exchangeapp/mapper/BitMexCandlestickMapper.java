package com.example.exchangeapp.mapper;

import com.example.exchangeapp.domain.BitMexCandlestickEntity;
import com.example.exchangeapp.dto.BitMexCandlestickDto;
import com.example.exchangeapp.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = UserService.class)
public interface BitMexCandlestickMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "created", ignore = true),
            @Mapping(target = "updated", ignore = true),
            @Mapping(target = "status", ignore = true),
    })
    BitMexCandlestickEntity mapToBitMexCandlestickEntity(BitMexCandlestickDto dto);

    BitMexCandlestickDto mapToBitMexCandlestickDto(BitMexCandlestickEntity entity);
}
