package com.example.tradingapp.mapper;

import com.example.tradingapp.dto.WbBidRequestDto;
import com.example.tradingapp.dto.WbBidResponseDto;
import com.example.tradingapp.entity.WbBid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WbBidMapper {

    WbBidMapper INSTANCE = Mappers.getMapper(WbBidMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "wbOrder", ignore = true)
    @Mapping(target = "wbOrderGroup", ignore = true)
    @Mapping(target = "wbShortNeed", ignore = true)
    @Mapping(target = "fills", ignore = true)
    WbBid toEntity(WbBidRequestDto dto);

    WbBidResponseDto toDto(WbBid entity);

    List<WbBidResponseDto> toDtoList(List<WbBid> entities);
} 