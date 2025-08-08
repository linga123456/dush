package com.example.tradingapp.unit.service;

import com.example.tradingapp.dto.WbShortNeedRequestDto;
import com.example.tradingapp.dto.WbShortNeedResponseDto;
import com.example.tradingapp.entity.WbShortNeed;
import com.example.tradingapp.mapper.WbShortNeedMapper;
import com.example.tradingapp.repository.WbShortNeedRepository;
import com.example.tradingapp.service.impl.WbShortNeedServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WbShortNeedServiceTest {

    @Mock
    private WbShortNeedRepository wbShortNeedRepository;

    @Mock
    private WbShortNeedMapper wbShortNeedMapper;

    @InjectMocks
    private WbShortNeedServiceImpl wbShortNeedService;

    private WbShortNeedRequestDto requestDto;
    private WbShortNeed entity;
    private WbShortNeedResponseDto responseDto;

    @BeforeEach
    void setUp() {
        requestDto = new WbShortNeedRequestDto();
        requestDto.setCreatedDate(LocalDate.of(2024, 1, 15));
        requestDto.setReceiveTime(LocalDateTime.of(2024, 1, 15, 10, 30));
        requestDto.setSecurityCode("AAPL");
        requestDto.setSettlementDate("2024-01-17");
        requestDto.setNeedType("SHORT");
        requestDto.setRunTime("2024-01-15T10:30:00");
        requestDto.setPartialFlag("Y");
        requestDto.setIsNewVersion("N");
        requestDto.setQuantity(new BigDecimal("1000.00"));
        requestDto.setDivStrategy("EQUAL");
        requestDto.setIsManual("N");

        entity = new WbShortNeed();
        entity.setId(1L);
        entity.setCreatedDate(LocalDate.of(2024, 1, 15));
        entity.setReceiveTime(LocalDateTime.of(2024, 1, 15, 10, 30));
        entity.setSecurityCode("AAPL");
        entity.setSettlementDate("2024-01-17");
        entity.setNeedType("SHORT");
        entity.setRunTime("2024-01-15T10:30:00");
        entity.setPartialFlag("Y");
        entity.setIsNewVersion("N");
        entity.setQuantity(new BigDecimal("1000.00"));
        entity.setDivStrategy("EQUAL");
        entity.setIsManual("N");

        responseDto = new WbShortNeedResponseDto();
        responseDto.setId(1L);
        responseDto.setCreatedDate(LocalDate.of(2024, 1, 15));
        responseDto.setReceiveTime(LocalDateTime.of(2024, 1, 15, 10, 30));
        responseDto.setSecurityCode("AAPL");
        responseDto.setSettlementDate("2024-01-17");
        responseDto.setNeedType("SHORT");
        responseDto.setRunTime("2024-01-15T10:30:00");
        responseDto.setPartialFlag("Y");
        responseDto.setIsNewVersion("N");
        responseDto.setQuantity(new BigDecimal("1000.00"));
        responseDto.setDivStrategy("EQUAL");
        responseDto.setIsManual("N");
    }

    @Test
    void createWbShortNeed_Success() {
        // Given
        when(wbShortNeedMapper.toEntity(requestDto)).thenReturn(entity);
        when(wbShortNeedRepository.save(any(WbShortNeed.class))).thenReturn(entity);
        when(wbShortNeedMapper.toDto(entity)).thenReturn(responseDto);

        // When
        WbShortNeedResponseDto result = wbShortNeedService.createWbShortNeed(requestDto);

        // Then
        assertNotNull(result);
        assertEquals(responseDto.getId(), result.getId());
        assertEquals(responseDto.getSecurityCode(), result.getSecurityCode());
        verify(wbShortNeedRepository).save(any(WbShortNeed.class));
    }

    @Test
    void getWbShortNeedById_Success() {
        // Given
        Long id = 1L;
        when(wbShortNeedRepository.findById(id)).thenReturn(Optional.of(entity));
        when(wbShortNeedMapper.toDto(entity)).thenReturn(responseDto);

        // When
        WbShortNeedResponseDto result = wbShortNeedService.getWbShortNeedById(id);

        // Then
        assertNotNull(result);
        assertEquals(responseDto.getId(), result.getId());
        verify(wbShortNeedRepository).findById(id);
    }

    @Test
    void getWbShortNeedById_NotFound() {
        // Given
        Long id = 999L;
        when(wbShortNeedRepository.findById(id)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(RuntimeException.class, () -> wbShortNeedService.getWbShortNeedById(id));
        verify(wbShortNeedRepository).findById(id);
    }

    @Test
    void getAllWbShortNeeds_Success() {
        // Given
        List<WbShortNeed> entities = Arrays.asList(entity);
        List<WbShortNeedResponseDto> expectedDtos = Arrays.asList(responseDto);
        when(wbShortNeedRepository.findAll()).thenReturn(entities);
        when(wbShortNeedMapper.toDtoList(entities)).thenReturn(expectedDtos);

        // When
        List<WbShortNeedResponseDto> result = wbShortNeedService.getAllWbShortNeeds();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(responseDto.getId(), result.get(0).getId());
        verify(wbShortNeedRepository).findAll();
    }

    @Test
    void getWbShortNeedsBySecurityCode_Success() {
        // Given
        String securityCode = "AAPL";
        List<WbShortNeed> entities = Arrays.asList(entity);
        List<WbShortNeedResponseDto> expectedDtos = Arrays.asList(responseDto);
        when(wbShortNeedRepository.findBySecurityCode(securityCode)).thenReturn(entities);
        when(wbShortNeedMapper.toDtoList(entities)).thenReturn(expectedDtos);

        // When
        List<WbShortNeedResponseDto> result = wbShortNeedService.getWbShortNeedsBySecurityCode(securityCode);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(responseDto.getSecurityCode(), result.get(0).getSecurityCode());
        verify(wbShortNeedRepository).findBySecurityCode(securityCode);
    }

    @Test
    void updateWbShortNeed_Success() {
        // Given
        Long id = 1L;
        when(wbShortNeedRepository.findById(id)).thenReturn(Optional.of(entity));
        when(wbShortNeedMapper.toEntity(requestDto)).thenReturn(entity);
        when(wbShortNeedRepository.save(any(WbShortNeed.class))).thenReturn(entity);
        when(wbShortNeedMapper.toDto(entity)).thenReturn(responseDto);

        // When
        WbShortNeedResponseDto result = wbShortNeedService.updateWbShortNeed(id, requestDto);

        // Then
        assertNotNull(result);
        assertEquals(responseDto.getId(), result.getId());
        verify(wbShortNeedRepository).findById(id);
        verify(wbShortNeedRepository).save(any(WbShortNeed.class));
    }

    @Test
    void deleteWbShortNeed_Success() {
        // Given
        Long id = 1L;
        when(wbShortNeedRepository.existsById(id)).thenReturn(true);

        // When
        wbShortNeedService.deleteWbShortNeed(id);

        // Then
        verify(wbShortNeedRepository).existsById(id);
        verify(wbShortNeedRepository).deleteById(id);
    }

    @Test
    void deleteWbShortNeed_NotFound() {
        // Given
        Long id = 999L;
        when(wbShortNeedRepository.existsById(id)).thenReturn(false);

        // When & Then
        assertThrows(RuntimeException.class, () -> wbShortNeedService.deleteWbShortNeed(id));
        verify(wbShortNeedRepository).existsById(id);
        verify(wbShortNeedRepository, never()).deleteById(id);
    }
} 