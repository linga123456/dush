package com.example.tradingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WbOrderResponseDto {

    private Long id;
    private Long orderId;
    private Long orderGroupId;
    private LocalDate createdDate;
    private LocalDateTime receiveTime;
    private String securityCode;
    private LocalDate settlementDate;
} 