package com.example.tradingapp.unit.controller;

import com.example.tradingapp.controller.WbFillController;
import com.example.tradingapp.dto.WbFillRequestDto;
import com.example.tradingapp.dto.WbFillResponseDto;
import com.example.tradingapp.service.WbFillService;
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
class WbFillControllerTest {
    @Mock
    private WbFillService wbFillService;
    @InjectMocks
    private WbFillController wbFillController;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private WbFillRequestDto requestDto;
    private WbFillResponseDto responseDto;
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(wbFillController).build();
        objectMapper = new ObjectMapper();
        requestDto = new WbFillRequestDto();
        responseDto = new WbFillResponseDto();
        responseDto.setId(1L);
    }
    @Test
    void createWbFill_Success() throws Exception {
        when(wbFillService.createWbFill(any(WbFillRequestDto.class))).thenReturn(responseDto);
        mockMvc.perform(post("/api/wb-fills")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }
    @Test
    void getWbFillById_Success() throws Exception {
        when(wbFillService.getWbFillById(1L)).thenReturn(responseDto);
        mockMvc.perform(get("/api/wb-fills/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }
    @Test
    void getAllWbFills_Success() throws Exception {
        List<WbFillResponseDto> dtos = Arrays.asList(responseDto);
        when(wbFillService.getAllWbFills()).thenReturn(dtos);
        mockMvc.perform(get("/api/wb-fills"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1));
    }
} 