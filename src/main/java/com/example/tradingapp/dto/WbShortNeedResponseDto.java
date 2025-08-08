package com.example.tradingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WbShortNeedResponseDto {

    private Long id;
    private LocalDate createdDate;
    private LocalDateTime receiveTime;
    private String securityCode;
    private String settlementDate;
    private String needType;
    private String runTime;
    private String partialFlag;
    private String isNewVersion;
    private BigDecimal quantity;
    private String divStrategy;
    private String isManual;
} 