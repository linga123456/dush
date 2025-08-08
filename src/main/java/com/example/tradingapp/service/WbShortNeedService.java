package com.example.tradingapp.service;

import com.example.tradingapp.dto.WbShortNeedRequestDto;
import com.example.tradingapp.dto.WbShortNeedResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface WbShortNeedService {

    WbShortNeedResponseDto createWbShortNeed(WbShortNeedRequestDto dto);

    WbShortNeedResponseDto getWbShortNeedByCorrelationId(String correlationId);

    List<WbShortNeedResponseDto> getAllWbShortNeeds();

    List<WbShortNeedResponseDto> getWbShortNeedsBySecurityCode(String securityCode);

    List<WbShortNeedResponseDto> getWbShortNeedsByCreatedDate(LocalDate createdDate);

    List<WbShortNeedResponseDto> getWbShortNeedsBySettlementDate(String settlementDate);

    List<WbShortNeedResponseDto> getWbShortNeedsByNeedType(String needType);

    WbShortNeedResponseDto updateWbShortNeed(String correlationId, WbShortNeedRequestDto dto);

    void deleteWbShortNeed(String correlationId);
} 