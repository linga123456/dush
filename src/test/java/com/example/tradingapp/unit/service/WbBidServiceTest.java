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
        entity = new WbBid();
        entity.setId(1L);
        responseDto = new WbBidResponseDto();
        responseDto.setId(1L);
    }
    @Test
    void createWbBid_Success() {
        when(wbBidMapper.toEntity(requestDto)).thenReturn(entity);
        when(wbBidRepository.save(any(WbBid.class))).thenReturn(entity);
        when(wbBidMapper.toDto(entity)).thenReturn(responseDto);
        WbBidResponseDto result = wbBidService.createWbBid(requestDto);
        assertNotNull(result);
        assertEquals(responseDto.getId(), result.getId());
        verify(wbBidRepository).save(any(WbBid.class));
    }
    @Test
    void getWbBidById_Success() {
        when(wbBidRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(wbBidMapper.toDto(entity)).thenReturn(responseDto);
        WbBidResponseDto result = wbBidService.getWbBidById(1L);
        assertNotNull(result);
        assertEquals(responseDto.getId(), result.getId());
    }
    @Test
    void getAllWbBids_Success() {
        List<WbBid> entities = Arrays.asList(entity);
        List<WbBidResponseDto> dtos = Arrays.asList(responseDto);
        when(wbBidRepository.findAll()).thenReturn(entities);
        when(wbBidMapper.toDtoList(entities)).thenReturn(dtos);
        List<WbBidResponseDto> result = wbBidService.getAllWbBids();
        assertEquals(1, result.size());
    }
    @Test
    void updateWbBid_Success() {
        when(wbBidRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(wbBidMapper.toEntity(requestDto)).thenReturn(entity);
        when(wbBidRepository.save(any(WbBid.class))).thenReturn(entity);
        when(wbBidMapper.toDto(entity)).thenReturn(responseDto);
        WbBidResponseDto result = wbBidService.updateWbBid(1L, requestDto);
        assertNotNull(result);
        assertEquals(responseDto.getId(), result.getId());
    }
    @Test
    void deleteWbBid_Success() {
        when(wbBidRepository.existsById(1L)).thenReturn(true);
        wbBidService.deleteWbBid(1L);
        verify(wbBidRepository).deleteById(1L);
    }
} 