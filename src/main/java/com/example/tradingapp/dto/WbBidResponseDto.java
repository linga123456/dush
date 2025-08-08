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
public class WbBidResponseDto {

    private Long id;
    private String bidId;
    private String batchId;
    private Long requestSequence;
    private String bidType;
    private Long ngtChainId;
    private Long equilendId;
    private BigDecimal quantityFilled;
    private String status;
    private String statusDescription;
    private Long cptyLeId;
    private Long cptyCorpId;
    private String lenderReferenceId;
    private BigDecimal rate;
    private BigDecimal fee;
    private String subAcct;
    private BigDecimal contractPrice;
    private BigDecimal collCashAmt;
    private String collCcy;
    private String collType;
    private String collDesc;
    private String traderName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long abSeqNbr;
    private LocalDateTime dateStamp;
    private Long orderId;
    private Long orderGroupId;
    private LocalDate createdDate;
    private LocalDateTime receiveTime;
    private String securityCode;
    private String settlementDate;
} 