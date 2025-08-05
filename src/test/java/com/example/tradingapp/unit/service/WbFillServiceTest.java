package com.example.tradingapp.unit.service;

import com.example.tradingapp.dto.WbFillRequestDto;
import com.example.tradingapp.dto.WbFillResponseDto;
import com.example.tradingapp.entity.WbFill;
import com.example.tradingapp.mapper.WbFillMapper;
import com.example.tradingapp.repository.WbFillRepository;
import com.example.tradingapp.service.impl.WbFillServiceImpl;
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
class WbFillServiceTest {
    @Mock
    private WbFillRepository wbFillRepository;
    @Mock
    private WbFillMapper wbFillMapper;
    @InjectMocks
    private WbFillServiceImpl wbFillService;
    private WbFillRequestDto requestDto;
    private WbFill entity;
    private WbFillResponseDto responseDto;
    @BeforeEach
    void setUp() {
        requestDto = new WbFillRequestDto();
        entity = new WbFill();
        entity.setId(1L);
        responseDto = new WbFillResponseDto();
        responseDto.setId(1L);
    }
    @Test
    void createWbFill_Success() {
        when(wbFillMapper.toEntity(requestDto)).thenReturn(entity);
        when(wbFillRepository.save(any(WbFill.class))).thenReturn(entity);
        when(wbFillMapper.toDto(entity)).thenReturn(responseDto);
        WbFillResponseDto result = wbFillService.createWbFill(requestDto);
        assertNotNull(result);
        assertEquals(responseDto.getId(), result.getId());
        verify(wbFillRepository).save(any(WbFill.class));
    }
    @Test
    void getWbFillById_Success() {
        when(wbFillRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(wbFillMapper.toDto(entity)).thenReturn(responseDto);
        WbFillResponseDto result = wbFillService.getWbFillById(1L);
        assertNotNull(result);
        assertEquals(responseDto.getId(), result.getId());
    }
    @Test
    void getAllWbFills_Success() {
        List<WbFill> entities = Arrays.asList(entity);
        List<WbFillResponseDto> dtos = Arrays.asList(responseDto);
        when(wbFillRepository.findAll()).thenReturn(entities);
        when(wbFillMapper.toDtoList(entities)).thenReturn(dtos);
        List<WbFillResponseDto> result = wbFillService.getAllWbFills();
        assertEquals(1, result.size());
    }
    @Test
    void updateWbFill_Success() {
        when(wbFillRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(wbFillMapper.toEntity(requestDto)).thenReturn(entity);
        when(wbFillRepository.save(any(WbFill.class))).thenReturn(entity);
        when(wbFillMapper.toDto(entity)).thenReturn(responseDto);
        WbFillResponseDto result = wbFillService.updateWbFill(1L, requestDto);
        assertNotNull(result);
        assertEquals(responseDto.getId(), result.getId());
    }
    @Test
    void deleteWbFill_Success() {
        when(wbFillRepository.existsById(1L)).thenReturn(true);
        wbFillService.deleteWbFill(1L);
        verify(wbFillRepository).deleteById(1L);
    }
} 