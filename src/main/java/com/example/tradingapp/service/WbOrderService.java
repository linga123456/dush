package com.example.tradingapp.service;

import com.example.tradingapp.dto.WbOrderRequestDto;
import com.example.tradingapp.dto.WbOrderResponseDto;

import java.util.List;

public interface WbOrderService {

    WbOrderResponseDto createWbOrder(WbOrderRequestDto dto);

    WbOrderResponseDto getWbOrderByOrderId(Long orderId);

    List<WbOrderResponseDto> getAllWbOrders();

    List<WbOrderResponseDto> getWbOrdersByOrderGroupId(Long orderGroupId);

    List<WbOrderResponseDto> getWbOrdersByCorrelationId(String correlationId);

    WbOrderResponseDto updateWbOrder(Long orderId, WbOrderRequestDto dto);

    void deleteWbOrder(Long orderId);
} 