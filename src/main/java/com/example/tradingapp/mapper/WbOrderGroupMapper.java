package com.example.tradingapp.mapper;

import com.example.tradingapp.dto.WbOrderGroupRequestDto;
import com.example.tradingapp.dto.WbOrderGroupResponseDto;
import com.example.tradingapp.entity.WbOrderGroup;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WbOrderGroupMapper {

    WbOrderGroupMapper INSTANCE = Mappers.getMapper(WbOrderGroupMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "bids", ignore = true)
    WbOrderGroup toEntity(WbOrderGroupRequestDto dto);

    WbOrderGroupResponseDto toDto(WbOrderGroup entity);

    List<WbOrderGroupResponseDto> toDtoList(List<WbOrderGroup> entities);
} 