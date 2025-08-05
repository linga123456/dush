package com.example.tradingapp.mapper;

import com.example.tradingapp.dto.WbOrderRequestDto;
import com.example.tradingapp.dto.WbOrderResponseDto;
import com.example.tradingapp.entity.WbOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WbOrderMapper {

    WbOrderMapper INSTANCE = Mappers.getMapper(WbOrderMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "wbOrderGroup", ignore = true)
    @Mapping(target = "wbShortNeed", ignore = true)
    @Mapping(target = "bids", ignore = true)
    @Mapping(target = "fills", ignore = true)
    WbOrder toEntity(WbOrderRequestDto dto);

    WbOrderResponseDto toDto(WbOrder entity);

    List<WbOrderResponseDto> toDtoList(List<WbOrder> entities);
} 