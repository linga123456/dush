package com.example.tradingapp.integration;

import com.example.tradingapp.dto.WbShortNeedRequestDto;
import com.example.tradingapp.dto.WbShortNeedResponseDto;
import com.example.tradingapp.repository.WbShortNeedRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@Transactional
class WbShortNeedIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private WbShortNeedRepository wbShortNeedRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        wbShortNeedRepository.deleteAll();
    }

    @Test
    void createWbShortNeed_Success() throws Exception {
        WbShortNeedRequestDto requestDto = createSampleRequestDto();

        mockMvc.perform(post("/api/wb-short-needs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.securityCode").value("AAPL"));
    }

    private WbShortNeedRequestDto createSampleRequestDto() {
        WbShortNeedRequestDto dto = new WbShortNeedRequestDto();
        dto.setCreatedDate(LocalDate.of(2024, 1, 15));
        dto.setReceiveTime(LocalDateTime.of(2024, 1, 15, 10, 30));
        dto.setSecurityCode("AAPL");
        dto.setSettlementDate("2024-01-17");
        dto.setNeedType("SHORT");
        dto.setRunTime("2024-01-15T10:30:00");
        dto.setPartialFlag("Y");
        dto.setIsNewVersion("N");
        dto.setQuantity(new BigDecimal("1000.00"));
        dto.setDivStrategy("EQUAL");
        dto.setIsManual("N");
        return dto;
    }
} 