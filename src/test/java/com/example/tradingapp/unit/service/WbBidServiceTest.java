package com.example.tradingapp.unit.service;

import com.example.tradingapp.dto.WbBidRequestDto;
import com.example.tradingapp.dto.WbBidResponseDto;
import com.example.tradingapp.entity.WbBid;
import com.example.tradingapp.mapper.WbBidMapper;
import com.example.tradingapp.repository.WbBidRepository;
import com.example.tradingapp.service.impl.WbBidServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WbBidServiceTest {
    @Mock
    private WbBidRepository wbBidRepository;
    @Mock
    private WbBidMapper wbBidMapper;
    @InjectMocks
    private WbBidServiceImpl wbBidService;
    private WbBidRequestDto requestDto;
    private WbBid entity;
    private WbBidResponseDto responseDto;
    
    @BeforeEach
    void setUp() {
        requestDto = new WbBidRequestDto();
        requestDto.setBidId("BID-001");
        requestDto.setBatchId("BATCH-001");
        requestDto.setCptyLeId(1001L);
        requestDto.setCptyCorpId(2001L);
        requestDto.setLenderReferenceId("LEND-001");
        requestDto.setCollType("CASH");
        requestDto.setTraderName("John Doe");
        requestDto.setStartTime(LocalDateTime.of(2024, 1, 15, 10, 30));
        requestDto.setEndTime(LocalDateTime.of(2024, 1, 15, 11, 30));
        requestDto.setAbSeqNbr(1L);
        requestDto.setDateStamp(LocalDateTime.of(2024, 1, 15, 10, 30));
        requestDto.setOrderId(3001L);
        requestDto.setOrderGroupId(4001L);
        requestDto.setCorrelationId("CORR-001");
        requestDto.setRateIndicator("FIXED");
        
        entity = new WbBid();
        entity.setBidId("BID-001");
        entity.setId(1L);
        entity.setBatchId("BATCH-001");
        entity.setCptyLeId(1001L);
        entity.setCptyCorpId(2001L);
        entity.setLenderReferenceId("LEND-001");
        entity.setCollType("CASH");
        entity.setTraderName("John Doe");
        entity.setStartTime(LocalDateTime.of(2024, 1, 15, 10, 30));
        entity.setEndTime(LocalDateTime.of(2024, 1, 15, 11, 30));
        entity.setAbSeqNbr(1L);
        entity.setDateStamp(LocalDateTime.of(2024, 1, 15, 10, 30));
        entity.setOrderId(3001L);
        entity.setOrderGroupId(4001L);
        entity.setCorrelationId("CORR-001");
        entity.setRateIndicator("FIXED");
        
        responseDto = new WbBidResponseDto();
        responseDto.setId(1L);
        responseDto.setBidId("BID-001");
        responseDto.setBatchId("BATCH-001");
        responseDto.setCptyLeId(1001L);
        responseDto.setCptyCorpId(2001L);
        responseDto.setLenderReferenceId("LEND-001");
        responseDto.setCollType("CASH");
        responseDto.setTraderName("John Doe");
        responseDto.setStartTime(LocalDateTime.of(2024, 1, 15, 10, 30));
        responseDto.setEndTime(LocalDateTime.of(2024, 1, 15, 11, 30));
        responseDto.setAbSeqNbr(1L);
        responseDto.setDateStamp(LocalDateTime.of(2024, 1, 15, 10, 30));
        responseDto.setOrderId(3001L);
        responseDto.setOrderGroupId(4001L);
        responseDto.setCorrelationId("CORR-001");
        responseDto.setRateIndicator("FIXED");
    }
    
    @Test
    void createWbBid_Success() {
        when(wbBidMapper.toEntity(requestDto)).thenReturn(entity);
        when(wbBidRepository.save(any(WbBid.class))).thenReturn(entity);
        when(wbBidMapper.toDto(entity)).thenReturn(responseDto);
        WbBidResponseDto result = wbBidService.createWbBid(requestDto);
        assertNotNull(result);
        assertEquals(responseDto.getBidId(), result.getBidId());
        verify(wbBidRepository).save(any(WbBid.class));
    }
    
    @Test
    void getWbBidByBidId_Success() {
        when(wbBidRepository.findByBidId("BID-001")).thenReturn(Optional.of(entity));
        when(wbBidMapper.toDto(entity)).thenReturn(responseDto);
        WbBidResponseDto result = wbBidService.getWbBidByBidId("BID-001");
        assertNotNull(result);
        assertEquals(responseDto.getBidId(), result.getBidId());
    }
    
    @Test
    void getAllWbBids_Success() {
        List<WbBid> entities = Arrays.asList(entity);
        List<WbBidResponseDto> dtos = Arrays.asList(responseDto);
        when(wbBidRepository.findAll()).thenReturn(entities);
        when(wbBidMapper.toDtoList(entities)).thenReturn(dtos);
        List<WbBidResponseDto> result = wbBidService.getAllWbBids();
        assertEquals(1, result.size());
        assertEquals(responseDto.getBidId(), result.get(0).getBidId());
    }
    
    @Test
    void updateWbBid_Success() {
        when(wbBidRepository.findByBidId("BID-001")).thenReturn(Optional.of(entity));
        when(wbBidMapper.toEntity(requestDto)).thenReturn(entity);
        when(wbBidRepository.save(any(WbBid.class))).thenReturn(entity);
        when(wbBidMapper.toDto(entity)).thenReturn(responseDto);
        WbBidResponseDto result = wbBidService.updateWbBid("BID-001", requestDto);
        assertNotNull(result);
        assertEquals(responseDto.getBidId(), result.getBidId());
    }
    
    @Test
    void deleteWbBid_Success() {
        when(wbBidRepository.findByBidId("BID-001")).thenReturn(Optional.of(entity));
        wbBidService.deleteWbBid("BID-001");
        verify(wbBidRepository).delete(entity);
    }
} 