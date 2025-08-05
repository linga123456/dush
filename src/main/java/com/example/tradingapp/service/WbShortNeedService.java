package com.example.tradingapp.service;

import com.example.tradingapp.dto.WbShortNeedRequestDto;
import com.example.tradingapp.dto.WbShortNeedResponseDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface WbShortNeedService {

    WbShortNeedResponseDto createWbShortNeed(WbShortNeedRequestDto dto);

    WbShortNeedResponseDto getWbShortNeedById(Long id);

    WbShortNeedResponseDto getWbShortNeedByCompositeKey(LocalDate createdDate, LocalDateTime receiveTime, 
                                                       String securityCode, LocalDate settlementDate);

    List<WbShortNeedResponseDto> getAllWbShortNeeds();

    List<WbShortNeedResponseDto> getWbShortNeedsBySecurityCode(String securityCode);

    List<WbShortNeedResponseDto> getWbShortNeedsByCreatedDate(LocalDate createdDate);

    List<WbShortNeedResponseDto> getWbShortNeedsBySettlementDate(LocalDate settlementDate);

    List<WbShortNeedResponseDto> getWbShortNeedsByNeedType(String needType);

    WbShortNeedResponseDto updateWbShortNeed(Long id, WbShortNeedRequestDto dto);

    void deleteWbShortNeed(Long id);
} 