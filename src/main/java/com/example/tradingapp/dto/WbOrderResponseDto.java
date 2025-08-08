package com.example.tradingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WbOrderResponseDto {

    private Long id;
    private Long orderId;
    private Long orderGroupId;
    private String correlationId;
    private BigDecimal fillQuantity;
} 