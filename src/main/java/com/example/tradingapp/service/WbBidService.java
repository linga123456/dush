package com.example.tradingapp.service;

import com.example.tradingapp.dto.WbBidRequestDto;
import com.example.tradingapp.dto.WbBidResponseDto;

import java.util.List;

public interface WbBidService {

    WbBidResponseDto createWbBid(WbBidRequestDto dto);

    WbBidResponseDto getWbBidById(Long id);

    WbBidResponseDto getWbBidByBidId(String bidId);

    List<WbBidResponseDto> getAllWbBids();

    List<WbBidResponseDto> getWbBidsByOrderId(Long orderId);

    List<WbBidResponseDto> getWbBidsByOrderGroupId(Long orderGroupId);

    List<WbBidResponseDto> getWbBidsByBatchId(String batchId);

    List<WbBidResponseDto> getWbBidsByStatus(String status);

    WbBidResponseDto updateWbBid(Long id, WbBidRequestDto dto);

    void deleteWbBid(Long id);
} 