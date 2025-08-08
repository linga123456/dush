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

import java.math.BigDecimal;
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
        requestDto.setOrderId(1001L);
        requestDto.setOrderGroupId(2001L);
        requestDto.setCorrelationId("CORR-001");
        requestDto.setFillQuantity(new BigDecimal("500.00"));
        
        entity = new WbOrder();
        entity.setId(1L);
        entity.setOrderId(1001L);
        entity.setOrderGroupId(2001L);
        entity.setCorrelationId("CORR-001");
        entity.setFillQuantity(new BigDecimal("500.00"));
        
        responseDto = new WbOrderResponseDto();
        responseDto.setId(1L);
        responseDto.setOrderId(1001L);
        responseDto.setOrderGroupId(2001L);
        responseDto.setCorrelationId("CORR-001");
        responseDto.setFillQuantity(new BigDecimal("500.00"));
    }
    
    @Test
    void createWbOrder_Success() {
        when(wbOrderMapper.toEntity(requestDto)).thenReturn(entity);
        when(wbOrderRepository.save(any(WbOrder.class))).thenReturn(entity);
        when(wbOrderMapper.toDto(entity)).thenReturn(responseDto);
        WbOrderResponseDto result = wbOrderService.createWbOrder(requestDto);
        assertNotNull(result);
        assertEquals(responseDto.getOrderId(), result.getOrderId());
        verify(wbOrderRepository).save(any(WbOrder.class));
    }
    
    @Test
    void getWbOrderByOrderId_Success() {
        when(wbOrderRepository.findByOrderId(1001L)).thenReturn(Optional.of(entity));
        when(wbOrderMapper.toDto(entity)).thenReturn(responseDto);
        WbOrderResponseDto result = wbOrderService.getWbOrderByOrderId(1001L);
        assertNotNull(result);
        assertEquals(responseDto.getOrderId(), result.getOrderId());
    }
    
    @Test
    void getAllWbOrders_Success() {
        List<WbOrder> entities = Arrays.asList(entity);
        List<WbOrderResponseDto> dtos = Arrays.asList(responseDto);
        when(wbOrderRepository.findAll()).thenReturn(entities);
        when(wbOrderMapper.toDtoList(entities)).thenReturn(dtos);
        List<WbOrderResponseDto> result = wbOrderService.getAllWbOrders();
        assertEquals(1, result.size());
        assertEquals(responseDto.getOrderId(), result.get(0).getOrderId());
    }
    
    @Test
    void getWbOrdersByOrderGroupId_Success() {
        List<WbOrder> entities = Arrays.asList(entity);
        List<WbOrderResponseDto> dtos = Arrays.asList(responseDto);
        when(wbOrderRepository.findByOrderGroupId(2001L)).thenReturn(entities);
        when(wbOrderMapper.toDtoList(entities)).thenReturn(dtos);
        List<WbOrderResponseDto> result = wbOrderService.getWbOrdersByOrderGroupId(2001L);
        assertEquals(1, result.size());
        assertEquals(responseDto.getOrderGroupId(), result.get(0).getOrderGroupId());
    }
    
    @Test
    void getWbOrdersByCorrelationId_Success() {
        List<WbOrder> entities = Arrays.asList(entity);
        List<WbOrderResponseDto> dtos = Arrays.asList(responseDto);
        when(wbOrderRepository.findByCorrelationId("CORR-001")).thenReturn(entities);
        when(wbOrderMapper.toDtoList(entities)).thenReturn(dtos);
        List<WbOrderResponseDto> result = wbOrderService.getWbOrdersByCorrelationId("CORR-001");
        assertEquals(1, result.size());
        assertEquals(responseDto.getCorrelationId(), result.get(0).getCorrelationId());
    }
    
    @Test
    void updateWbOrder_Success() {
        when(wbOrderRepository.findByOrderId(1001L)).thenReturn(Optional.of(entity));
        when(wbOrderMapper.toEntity(requestDto)).thenReturn(entity);
        when(wbOrderRepository.save(any(WbOrder.class))).thenReturn(entity);
        when(wbOrderMapper.toDto(entity)).thenReturn(responseDto);
        WbOrderResponseDto result = wbOrderService.updateWbOrder(1001L, requestDto);
        assertNotNull(result);
        assertEquals(responseDto.getOrderId(), result.getOrderId());
    }
    
    @Test
    void deleteWbOrder_Success() {
        when(wbOrderRepository.findByOrderId(1001L)).thenReturn(Optional.of(entity));
        wbOrderService.deleteWbOrder(1001L);
        verify(wbOrderRepository).delete(entity);
    }
} 