package com.example.tradingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WbOrderGroupResponseDto {

    private Long orderGroupId;
    private String userId;
    private LocalDateTime buttonClickTime;
    private Long numberOfShortsSelected;
    private Long id;
    private Long numberOfMarketsSelected;
    private String bidOption;
} 