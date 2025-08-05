package com.example.tradingapp.unit.controller;

import com.example.tradingapp.controller.WbOrderController;
import com.example.tradingapp.dto.WbOrderRequestDto;
import com.example.tradingapp.dto.WbOrderResponseDto;
import com.example.tradingapp.service.WbOrderService;
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
class WbOrderControllerTest {
    @Mock
    private WbOrderService wbOrderService;
    @InjectMocks
    private WbOrderController wbOrderController;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private WbOrderRequestDto requestDto;
    private WbOrderResponseDto responseDto;
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(wbOrderController).build();
        objectMapper = new ObjectMapper();
        requestDto = new WbOrderRequestDto();
        responseDto = new WbOrderResponseDto();
        responseDto.setId(1L);
    }
    @Test
    void createWbOrder_Success() throws Exception {
        when(wbOrderService.createWbOrder(any(WbOrderRequestDto.class))).thenReturn(responseDto);
        mockMvc.perform(post("/api/wb-orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }
    @Test
    void getWbOrderById_Success() throws Exception {
        when(wbOrderService.getWbOrderById(1L)).thenReturn(responseDto);
        mockMvc.perform(get("/api/wb-orders/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }
    @Test
    void getAllWbOrders_Success() throws Exception {
        List<WbOrderResponseDto> dtos = Arrays.asList(responseDto);
        when(wbOrderService.getAllWbOrders()).thenReturn(dtos);
        mockMvc.perform(get("/api/wb-orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1));
    }
} 