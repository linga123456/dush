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
        requestDto.setCreatedDate(LocalDate.of(2024, 1, 15));
        requestDto.setReceiveTime(LocalDateTime.of(2024, 1, 15, 10, 30));
        requestDto.setSecurityCode("AAPL");
        requestDto.setSettlementDate(LocalDate.of(2024, 1, 17));
        requestDto.setNeedType("SHORT");
        requestDto.setRunTime(LocalDateTime.of(2024, 1, 15, 10, 30));
        requestDto.setPartialFlag("Y");
        requestDto.setIsNewVersion("N");
        requestDto.setQuantity(new BigDecimal("1000.00"));
        requestDto.setDivStrategy("EQUAL");
        requestDto.setIsManual("N");

        responseDto = new WbShortNeedResponseDto();
        responseDto.setId(1L);
        responseDto.setCreatedDate(LocalDate.of(2024, 1, 15));
        responseDto.setReceiveTime(LocalDateTime.of(2024, 1, 15, 10, 30));
        responseDto.setSecurityCode("AAPL");
        responseDto.setSettlementDate(LocalDate.of(2024, 1, 17));
        responseDto.setNeedType("SHORT");
        responseDto.setRunTime(LocalDateTime.of(2024, 1, 15, 10, 30));
        responseDto.setPartialFlag("Y");
        responseDto.setIsNewVersion("N");
        responseDto.setQuantity(new BigDecimal("1000.00"));
        responseDto.setDivStrategy("EQUAL");
        responseDto.setIsManual("N");
    }

    @Test
    void createWbShortNeed_Success() throws Exception {
        when(wbShortNeedService.createWbShortNeed(any(WbShortNeedRequestDto.class)))
                .thenReturn(responseDto);

        mockMvc.perform(post("/api/wb-short-needs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.securityCode").value("AAPL"));
    }

    @Test
    void getWbShortNeedById_Success() throws Exception {
        when(wbShortNeedService.getWbShortNeedById(1L))
                .thenReturn(responseDto);

        mockMvc.perform(get("/api/wb-short-needs/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.securityCode").value("AAPL"));
    }
} 