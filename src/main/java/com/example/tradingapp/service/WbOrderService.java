package com.example.tradingapp.service;

import com.example.tradingapp.dto.WbOrderRequestDto;
import com.example.tradingapp.dto.WbOrderResponseDto;

import java.util.List;

public interface WbOrderService {

    WbOrderResponseDto createWbOrder(WbOrderRequestDto dto);

    WbOrderResponseDto getWbOrderById(Long id);

    WbOrderResponseDto getWbOrderByOrderId(Long orderId);

    List<WbOrderResponseDto> getAllWbOrders();

    List<WbOrderResponseDto> getWbOrdersByOrderGroupId(Long orderGroupId);

    List<WbOrderResponseDto> getWbOrdersBySecurityCode(String securityCode);

    WbOrderResponseDto updateWbOrder(Long id, WbOrderRequestDto dto);

    void deleteWbOrder(Long id);
} 