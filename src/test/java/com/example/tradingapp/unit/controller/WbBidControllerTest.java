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
        responseDto = new WbBidResponseDto();
        responseDto.setId(1L);
    }
    @Test
    void createWbBid_Success() throws Exception {
        when(wbBidService.createWbBid(any(WbBidRequestDto.class))).thenReturn(responseDto);
        mockMvc.perform(post("/api/wb-bids")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }
    @Test
    void getWbBidById_Success() throws Exception {
        when(wbBidService.getWbBidById(1L)).thenReturn(responseDto);
        mockMvc.perform(get("/api/wb-bids/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }
    @Test
    void getAllWbBids_Success() throws Exception {
        List<WbBidResponseDto> dtos = Arrays.asList(responseDto);
        when(wbBidService.getAllWbBids()).thenReturn(dtos);
        mockMvc.perform(get("/api/wb-bids"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1));
    }
} 