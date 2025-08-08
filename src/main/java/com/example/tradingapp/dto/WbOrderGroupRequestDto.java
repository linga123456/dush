package com.example.tradingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WbOrderGroupRequestDto {

    @NotNull(message = "Order group ID is required")
    private Long orderGroupId;

    @NotBlank(message = "User ID is required")
    private String userId;

    @NotNull(message = "Button click time is required")
    private LocalDateTime buttonClickTime;

    @NotNull(message = "Number of shorts selected is required")
    @Min(value = 0, message = "Number of shorts selected must be non-negative")
    private Long numberOfShortsSelected;

    @NotNull(message = "Number of markets selected is required")
    @Min(value = 0, message = "Number of markets selected must be non-negative")
    private Long numberOfMarketsSelected;
} 