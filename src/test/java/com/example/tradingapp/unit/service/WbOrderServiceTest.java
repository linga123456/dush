package com.example.tradingapp.unit.service;

import com.example.tradingapp.dto.WbOrderRequestDto;
import com.example.tradingapp.dto.WbOrderResponseDto;
import com.example.tradingapp.entity.WbOrder;
import com.example.tradingapp.mapper.WbOrderMapper;
import com.example.tradingapp.repository.WbOrderRepository;
import com.example.tradingapp.service.impl.WbOrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WbOrderServiceTest {
    @Mock
    private WbOrderRepository wbOrderRepository;
    @Mock
    private WbOrderMapper wbOrderMapper;
    @InjectMocks
    private WbOrderServiceImpl wbOrderService;
    private WbOrderRequestDto requestDto;
    private WbOrder entity;
    private WbOrderResponseDto responseDto;
    @BeforeEach
    void setUp() {
        requestDto = new WbOrderRequestDto();
        requestDto.setOrderId(1L);
        requestDto.setOrderGroupId(1L);
        requestDto.setCreatedDate(LocalDate.now());
        requestDto.setReceiveTime(LocalDateTime.now());
        requestDto.setSecurityCode("AAPL");
        requestDto.setSettlementDate("2024-01-17");
        entity = new WbOrder();
        entity.setId(1L);
        responseDto = new WbOrderResponseDto();
        responseDto.setId(1L);
    }
    @Test
    void createWbOrder_Success() {
        when(wbOrderMapper.toEntity(requestDto)).thenReturn(entity);
        when(wbOrderRepository.save(any(WbOrder.class))).thenReturn(entity);
        when(wbOrderMapper.toDto(entity)).thenReturn(responseDto);
        WbOrderResponseDto result = wbOrderService.createWbOrder(requestDto);
        assertNotNull(result);
        assertEquals(responseDto.getId(), result.getId());
        verify(wbOrderRepository).save(any(WbOrder.class));
    }
    @Test
    void getWbOrderById_Success() {
        when(wbOrderRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(wbOrderMapper.toDto(entity)).thenReturn(responseDto);
        WbOrderResponseDto result = wbOrderService.getWbOrderById(1L);
        assertNotNull(result);
        assertEquals(responseDto.getId(), result.getId());
    }
    @Test
    void getAllWbOrders_Success() {
        List<WbOrder> entities = Arrays.asList(entity);
        List<WbOrderResponseDto> dtos = Arrays.asList(responseDto);
        when(wbOrderRepository.findAll()).thenReturn(entities);
        when(wbOrderMapper.toDtoList(entities)).thenReturn(dtos);
        List<WbOrderResponseDto> result = wbOrderService.getAllWbOrders();
        assertEquals(1, result.size());
    }
    @Test
    void updateWbOrder_Success() {
        when(wbOrderRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(wbOrderMapper.toEntity(requestDto)).thenReturn(entity);
        when(wbOrderRepository.save(any(WbOrder.class))).thenReturn(entity);
        when(wbOrderMapper.toDto(entity)).thenReturn(responseDto);
        WbOrderResponseDto result = wbOrderService.updateWbOrder(1L, requestDto);
        assertNotNull(result);
        assertEquals(responseDto.getId(), result.getId());
    }
    @Test
    void deleteWbOrder_Success() {
        when(wbOrderRepository.existsById(1L)).thenReturn(true);
        wbOrderService.deleteWbOrder(1L);
        verify(wbOrderRepository).deleteById(1L);
    }
} 