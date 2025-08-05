package com.example.tradingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WbFillRequestDto {

    @NotNull(message = "Ticket ID is required")
    private Long ticketId;

    @NotNull(message = "Order ID is required")
    private Long orderId;

    @NotBlank(message = "Bid ID is required")
    private String bidId;
} 