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
        requestDto.setCorrelationId("CORR-001");
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
        requestDto.setPthQty(new BigDecimal("500.00"));
        requestDto.setEtfQuantoQty(new BigDecimal("200.00"));
        requestDto.setCollateralRecallQty(new BigDecimal("100.00"));
        requestDto.setAdjustedQty(new BigDecimal("800.00"));
        requestDto.setWashQty(new BigDecimal("50.00"));
        requestDto.setPmid("PMID-001");

        entity = new WbShortNeed();
        entity.setCorrelationId("CORR-001");
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
        entity.setPthQty(new BigDecimal("500.00"));
        entity.setEtfQuantoQty(new BigDecimal("200.00"));
        entity.setCollateralRecallQty(new BigDecimal("100.00"));
        entity.setAdjustedQty(new BigDecimal("800.00"));
        entity.setWashQty(new BigDecimal("50.00"));
        entity.setPmid("PMID-001");

        responseDto = new WbShortNeedResponseDto();
        responseDto.setCorrelationId("CORR-001");
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
        responseDto.setPthQty(new BigDecimal("500.00"));
        responseDto.setEtfQuantoQty(new BigDecimal("200.00"));
        responseDto.setCollateralRecallQty(new BigDecimal("100.00"));
        responseDto.setAdjustedQty(new BigDecimal("800.00"));
        responseDto.setWashQty(new BigDecimal("50.00"));
        responseDto.setPmid("PMID-001");
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
        assertEquals(responseDto.getCorrelationId(), result.getCorrelationId());
        assertEquals(responseDto.getSecurityCode(), result.getSecurityCode());
        verify(wbShortNeedRepository).save(any(WbShortNeed.class));
    }

    @Test
    void createWbShortNeed_WithNullPmid_Success() {
        // Given
        requestDto.setPmid(null);
        entity.setPmid(null);
        responseDto.setPmid(null);
        
        when(wbShortNeedMapper.toEntity(requestDto)).thenReturn(entity);
        when(wbShortNeedRepository.save(any(WbShortNeed.class))).thenReturn(entity);
        when(wbShortNeedMapper.toDto(entity)).thenReturn(responseDto);

        // When
        WbShortNeedResponseDto result = wbShortNeedService.createWbShortNeed(requestDto);

        // Then
        assertNotNull(result);
        assertEquals(responseDto.getCorrelationId(), result.getCorrelationId());
        assertEquals(responseDto.getSecurityCode(), result.getSecurityCode());
        assertNull(result.getPmid());
        verify(wbShortNeedRepository).save(any(WbShortNeed.class));
    }

    @Test
    void getWbShortNeedByCorrelationId_Success() {
        // Given
        String correlationId = "CORR-001";
        when(wbShortNeedRepository.findById(correlationId)).thenReturn(Optional.of(entity));
        when(wbShortNeedMapper.toDto(entity)).thenReturn(responseDto);

        // When
        WbShortNeedResponseDto result = wbShortNeedService.getWbShortNeedByCorrelationId(correlationId);

        // Then
        assertNotNull(result);
        assertEquals(responseDto.getCorrelationId(), result.getCorrelationId());
        verify(wbShortNeedRepository).findById(correlationId);
    }

    @Test
    void getWbShortNeedByCorrelationId_NotFound() {
        // Given
        String correlationId = "CORR-999";
        when(wbShortNeedRepository.findById(correlationId)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(RuntimeException.class, () -> wbShortNeedService.getWbShortNeedByCorrelationId(correlationId));
        verify(wbShortNeedRepository).findById(correlationId);
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
        assertEquals(responseDto.getCorrelationId(), result.get(0).getCorrelationId());
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
        String correlationId = "CORR-001";
        when(wbShortNeedRepository.findById(correlationId)).thenReturn(Optional.of(entity));
        when(wbShortNeedMapper.toEntity(requestDto)).thenReturn(entity);
        when(wbShortNeedRepository.save(any(WbShortNeed.class))).thenReturn(entity);
        when(wbShortNeedMapper.toDto(entity)).thenReturn(responseDto);

        // When
        WbShortNeedResponseDto result = wbShortNeedService.updateWbShortNeed(correlationId, requestDto);

        // Then
        assertNotNull(result);
        assertEquals(responseDto.getCorrelationId(), result.getCorrelationId());
        verify(wbShortNeedRepository).findById(correlationId);
        verify(wbShortNeedRepository).save(any(WbShortNeed.class));
    }

    @Test
    void deleteWbShortNeed_Success() {
        // Given
        String correlationId = "CORR-001";
        when(wbShortNeedRepository.existsById(correlationId)).thenReturn(true);

        // When
        wbShortNeedService.deleteWbShortNeed(correlationId);

        // Then
        verify(wbShortNeedRepository).existsById(correlationId);
        verify(wbShortNeedRepository).deleteById(correlationId);
    }

    @Test
    void deleteWbShortNeed_NotFound() {
        // Given
        String correlationId = "CORR-999";
        when(wbShortNeedRepository.existsById(correlationId)).thenReturn(false);

        // When & Then
        assertThrows(RuntimeException.class, () -> wbShortNeedService.deleteWbShortNeed(correlationId));
        verify(wbShortNeedRepository).existsById(correlationId);
        verify(wbShortNeedRepository, never()).deleteById(correlationId);
    }
} 