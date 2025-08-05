package com.example.tradingapp.mapper;

import com.example.tradingapp.dto.WbFillRequestDto;
import com.example.tradingapp.dto.WbFillResponseDto;
import com.example.tradingapp.entity.WbFill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WbFillMapper {

    WbFillMapper INSTANCE = Mappers.getMapper(WbFillMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "wbOrder", ignore = true)
    @Mapping(target = "wbBid", ignore = true)
    WbFill toEntity(WbFillRequestDto dto);

    WbFillResponseDto toDto(WbFill entity);

    List<WbFillResponseDto> toDtoList(List<WbFill> entities);
} 