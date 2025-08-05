package com.example.tradingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WbFillResponseDto {

    private Long id;
    private Long ticketId;
    private Long orderId;
    private String bidId;
} 