package com.example.tradingapp.service;

import com.example.tradingapp.dto.WbFillRequestDto;
import com.example.tradingapp.dto.WbFillResponseDto;

import java.util.List;

public interface WbFillService {

    WbFillResponseDto createWbFill(WbFillRequestDto dto);

    WbFillResponseDto getWbFillById(Long id);

    WbFillResponseDto getWbFillByTicketId(Long ticketId);

    List<WbFillResponseDto> getAllWbFills();

    List<WbFillResponseDto> getWbFillsByOrderId(Long orderId);

    List<WbFillResponseDto> getWbFillsByBidId(String bidId);

    WbFillResponseDto updateWbFill(Long id, WbFillRequestDto dto);

    void deleteWbFill(Long id);
} 