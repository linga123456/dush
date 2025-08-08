package com.example.tradingapp.mapper;

import com.example.tradingapp.dto.WbShortNeedRequestDto;
import com.example.tradingapp.dto.WbShortNeedResponseDto;
import com.example.tradingapp.entity.WbShortNeed;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WbShortNeedMapper {

    WbShortNeedMapper INSTANCE = Mappers.getMapper(WbShortNeedMapper.class);

    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "bids", ignore = true)
    WbShortNeed toEntity(WbShortNeedRequestDto dto);

    WbShortNeedResponseDto toDto(WbShortNeed entity);

    List<WbShortNeedResponseDto> toDtoList(List<WbShortNeed> entities);
} 