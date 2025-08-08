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

import java.math.BigDecimal;
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
        requestDto.setOrderId(1001L);
        requestDto.setOrderGroupId(2001L);
        requestDto.setCorrelationId("CORR-001");
        requestDto.setFillQuantity(new BigDecimal("500.00"));
        
        responseDto = new WbOrderResponseDto();
        responseDto.setId(1L);
        responseDto.setOrderId(1001L);
        responseDto.setOrderGroupId(2001L);
        responseDto.setCorrelationId("CORR-001");
        responseDto.setFillQuantity(new BigDecimal("500.00"));
    }
    
    @Test
    void createWbOrder_Success() throws Exception {
        when(wbOrderService.createWbOrder(any(WbOrderRequestDto.class))).thenReturn(responseDto);
        mockMvc.perform(post("/api/wb-orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.orderId").value(1001))
                .andExpect(jsonPath("$.correlationId").value("CORR-001"));
    }
    
    @Test
    void getWbOrderByOrderId_Success() throws Exception {
        when(wbOrderService.getWbOrderByOrderId(1001L)).thenReturn(responseDto);
        mockMvc.perform(get("/api/wb-orders/order/1001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").value(1001))
                .andExpect(jsonPath("$.correlationId").value("CORR-001"));
    }
    
    @Test
    void getAllWbOrders_Success() throws Exception {
        List<WbOrderResponseDto> dtos = Arrays.asList(responseDto);
        when(wbOrderService.getAllWbOrders()).thenReturn(dtos);
        mockMvc.perform(get("/api/wb-orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].orderId").value(1001));
    }
    
    @Test
    void getWbOrdersByOrderGroupId_Success() throws Exception {
        List<WbOrderResponseDto> dtos = Arrays.asList(responseDto);
        when(wbOrderService.getWbOrdersByOrderGroupId(2001L)).thenReturn(dtos);
        mockMvc.perform(get("/api/wb-orders/order-group/2001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].orderGroupId").value(2001));
    }
    
    @Test
    void getWbOrdersByCorrelationId_Success() throws Exception {
        List<WbOrderResponseDto> dtos = Arrays.asList(responseDto);
        when(wbOrderService.getWbOrdersByCorrelationId("CORR-001")).thenReturn(dtos);
        mockMvc.perform(get("/api/wb-orders/correlation/CORR-001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].correlationId").value("CORR-001"));
    }
    
    @Test
    void updateWbOrder_Success() throws Exception {
        when(wbOrderService.updateWbOrder(1001L, any(WbOrderRequestDto.class))).thenReturn(responseDto);
        mockMvc.perform(put("/api/wb-orders/order/1001")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").value(1001));
    }
    
    @Test
    void deleteWbOrder_Success() throws Exception {
        mockMvc.perform(delete("/api/wb-orders/order/1001"))
                .andExpect(status().isNoContent());
    }
} 