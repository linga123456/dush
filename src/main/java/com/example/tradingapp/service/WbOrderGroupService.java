package com.example.tradingapp.service;

import com.example.tradingapp.dto.WbOrderGroupRequestDto;
import com.example.tradingapp.dto.WbOrderGroupResponseDto;

import java.util.List;

public interface WbOrderGroupService {

    WbOrderGroupResponseDto createWbOrderGroup(WbOrderGroupRequestDto dto);

    WbOrderGroupResponseDto getWbOrderGroupById(Long id);

    WbOrderGroupResponseDto getWbOrderGroupByOrderGroupId(Long orderGroupId);

    List<WbOrderGroupResponseDto> getAllWbOrderGroups();

    List<WbOrderGroupResponseDto> getWbOrderGroupsByUserId(String userId);

    WbOrderGroupResponseDto updateWbOrderGroup(Long id, WbOrderGroupRequestDto dto);

    void deleteWbOrderGroup(Long id);
} 