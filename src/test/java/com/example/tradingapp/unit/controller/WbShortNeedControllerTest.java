package com.example.tradingapp.unit.controller;

import com.example.tradingapp.dto.WbShortNeedRequestDto;
import com.example.tradingapp.dto.WbShortNeedResponseDto;
import com.example.tradingapp.service.WbShortNeedService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class WbShortNeedControllerTest {

    @Mock
    private WbShortNeedService wbShortNeedService;

    @InjectMocks
    private com.example.tradingapp.controller.WbShortNeedController wbShortNeedController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    private WbShortNeedRequestDto requestDto;
    private WbShortNeedResponseDto responseDto;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(wbShortNeedController).build();
        objectMapper = new ObjectMapper();

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
    void createWbShortNeed_Success() throws Exception {
        when(wbShortNeedService.createWbShortNeed(any(WbShortNeedRequestDto.class)))
                .thenReturn(responseDto);

        mockMvc.perform(post("/api/wb-short-needs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.correlationId").value("CORR-001"))
                .andExpect(jsonPath("$.securityCode").value("AAPL"));
    }

    @Test
    void getWbShortNeedByCorrelationId_Success() throws Exception {
        when(wbShortNeedService.getWbShortNeedByCorrelationId("CORR-001"))
                .thenReturn(responseDto);

        mockMvc.perform(get("/api/wb-short-needs/CORR-001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.correlationId").value("CORR-001"))
                .andExpect(jsonPath("$.securityCode").value("AAPL"));
    }

    @Test
    void getAllWbShortNeeds_Success() throws Exception {
        List<WbShortNeedResponseDto> responseList = Arrays.asList(responseDto);
        when(wbShortNeedService.getAllWbShortNeeds()).thenReturn(responseList);

        mockMvc.perform(get("/api/wb-short-needs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].correlationId").value("CORR-001"));
    }

    @Test
    void updateWbShortNeed_Success() throws Exception {
        when(wbShortNeedService.updateWbShortNeed("CORR-001", any(WbShortNeedRequestDto.class)))
                .thenReturn(responseDto);

        mockMvc.perform(put("/api/wb-short-needs/CORR-001")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.correlationId").value("CORR-001"));
    }

    @Test
    void createWbShortNeed_WithNullPmid_Success() throws Exception {
        requestDto.setPmid(null);
        responseDto.setPmid(null);
        
        when(wbShortNeedService.createWbShortNeed(any(WbShortNeedRequestDto.class)))
                .thenReturn(responseDto);

        mockMvc.perform(post("/api/wb-short-needs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.correlationId").value("CORR-001"))
                .andExpect(jsonPath("$.securityCode").value("AAPL"))
                .andExpect(jsonPath("$.pmid").isEmpty());
    }

    @Test
    void deleteWbShortNeed_Success() throws Exception {
        mockMvc.perform(delete("/api/wb-short-needs/CORR-001"))
                .andExpect(status().isNoContent());
    }
} 