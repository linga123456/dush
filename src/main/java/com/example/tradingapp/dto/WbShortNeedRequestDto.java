package com.example.tradingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WbShortNeedRequestDto {

    @NotNull(message = "Created date is required")
    private LocalDate createdDate;

    @NotNull(message = "Receive time is required")
    private LocalDateTime receiveTime;

    @NotBlank(message = "Security code is required")
    private String securityCode;

    @NotNull(message = "Settlement date is required")
    private String settlementDate;

    @NotBlank(message = "Need type is required")
    private String needType;

    @NotNull(message = "Run time is required")
    private String runTime;

    @NotBlank(message = "Partial flag is required")
    private String partialFlag;

    @NotBlank(message = "Is new version flag is required")
    private String isNewVersion;

    @NotNull(message = "Quantity is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Quantity must be greater than 0")
    private BigDecimal quantity;

    @NotBlank(message = "Div strategy is required")
    private String divStrategy;

    @NotBlank(message = "Is manual flag is required")
    private String isManual;
} 