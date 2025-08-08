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
public class WbBidRequestDto {

    @NotBlank(message = "Bid ID is required")
    private String bidId;

    @NotBlank(message = "Batch ID is required")
    private String batchId;

    private Long requestSequence;
    private String bidType;
    private Long ngtChainId;
    private Long equilendId;
    private BigDecimal quantityFilled;
    private String status;
    private String statusDescription;

    @NotNull(message = "Counterparty LE ID is required")
    private Long cptyLeId;

    @NotNull(message = "Counterparty Corp ID is required")
    private Long cptyCorpId;

    @NotBlank(message = "Lender reference ID is required")
    private String lenderReferenceId;

    @NotNull(message = "Rate is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Rate must be greater than 0")
    private BigDecimal rate;

    @NotNull(message = "Fee is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Fee must be greater than 0")
    private BigDecimal fee;

    private String subAcct;
    private BigDecimal contractPrice;
    private BigDecimal collCashAmt;
    private String collCcy;

    @NotBlank(message = "Collateral type is required")
    private String collType;

    private String collDesc;

    @NotBlank(message = "Trader name is required")
    private String traderName;

    @NotNull(message = "Start time is required")
    private LocalDateTime startTime;

    @NotNull(message = "End time is required")
    private LocalDateTime endTime;

    @NotNull(message = "AB sequence number is required")
    private Long abSeqNbr;

    @NotNull(message = "Date stamp is required")
    private LocalDateTime dateStamp;

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
    private String settlementDate;
} 