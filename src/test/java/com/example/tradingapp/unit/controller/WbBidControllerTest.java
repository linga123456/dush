package com.example.tradingapp.unit.controller;

import com.example.tradingapp.controller.WbBidController;
import com.example.tradingapp.dto.WbBidRequestDto;
import com.example.tradingapp.dto.WbBidResponseDto;
import com.example.tradingapp.service.WbBidService;
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
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class WbBidControllerTest {
    @Mock
    private WbBidService wbBidService;
    @InjectMocks
    private WbBidController wbBidController;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private WbBidRequestDto requestDto;
    private WbBidResponseDto responseDto;
    
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(wbBidController).build();
        objectMapper = new ObjectMapper();
        
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
    void createWbBid_Success() throws Exception {
        when(wbBidService.createWbBid(any(WbBidRequestDto.class))).thenReturn(responseDto);
        mockMvc.perform(post("/api/wb-bids")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.bidId").value("BID-001"))
                .andExpect(jsonPath("$.correlationId").value("CORR-001"));
    }
    
    @Test
    void getWbBidByBidId_Success() throws Exception {
        when(wbBidService.getWbBidByBidId("BID-001")).thenReturn(responseDto);
        mockMvc.perform(get("/api/wb-bids/bid/BID-001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bidId").value("BID-001"))
                .andExpect(jsonPath("$.correlationId").value("CORR-001"));
    }
    
    @Test
    void getAllWbBids_Success() throws Exception {
        List<WbBidResponseDto> dtos = Arrays.asList(responseDto);
        when(wbBidService.getAllWbBids()).thenReturn(dtos);
        mockMvc.perform(get("/api/wb-bids"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].bidId").value("BID-001"));
    }
    
    @Test
    void updateWbBid_Success() throws Exception {
        when(wbBidService.updateWbBid("BID-001", any(WbBidRequestDto.class))).thenReturn(responseDto);
        mockMvc.perform(put("/api/wb-bids/bid/BID-001")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bidId").value("BID-001"));
    }
    
    @Test
    void deleteWbBid_Success() throws Exception {
        mockMvc.perform(delete("/api/wb-bids/bid/BID-001"))
                .andExpect(status().isNoContent());
    }
} 