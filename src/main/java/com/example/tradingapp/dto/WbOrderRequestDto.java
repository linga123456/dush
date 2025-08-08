package com.example.tradingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WbOrderRequestDto {

    @NotNull(message = "Order ID is required")
    private Long orderId;

    @NotNull(message = "Order group ID is required")
    private Long orderGroupId;

    @NotBlank(message = "Correlation ID is required")
    private String correlationId;

    @DecimalMin(value = "0.0", inclusive = false, message = "Fill quantity must be greater than 0")
    private BigDecimal fillQuantity;
} 