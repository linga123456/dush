package com.example.tradingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WbOrderRequestDto {

    @NotNull(message = "Order ID is required")
    private Long orderId;

    @NotNull(message = "Order group ID is required")
    private Long orderGroupId;

    @NotNull(message = "Created date is required")
    private LocalDate createdDate;

    @NotNull(message = "Receive time is required")
    private LocalDateTime receiveTime;

    @NotBlank(message = "Security code is required")
    private String securityCode;

    @NotNull(message = "Settlement date is required")
    private LocalDate settlementDate;
} 