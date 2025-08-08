package com.example.tradingapp.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "WB_SHORT_NEED")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WbShortNeed {

    @Id
    @Column(name = "CorrelationID", nullable = false, length = 255)
    private String correlationId;

    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CreatedDate", nullable = false)
    private LocalDate createdDate;

    @Column(name = "ReceiveTime", nullable = false)
    private LocalDateTime receiveTime;

    @Column(name = "SecurityCode", nullable = false, length = 255)
    private String securityCode;

    @Column(name = "SettlementDate", nullable = false, length = 255)
    private String settlementDate;

    @Column(name = "NeedType", nullable = false, length = 255)
    private String needType;

    @Column(name = "RunTime", nullable = false, length = 10)
    private String runTime;

    @Column(name = "PartialFlag", nullable = false, length = 1)
    private String partialFlag;

    @Column(name = "IsNewVersion", nullable = false, length = 1)
    private String isNewVersion;

    @Column(name = "Quantity", nullable = false, precision = 20, scale = 2)
    private BigDecimal quantity;

    @Column(name = "DivStrategy", nullable = false, length = 255)
    private String divStrategy;

    @Column(name = "IsManual", nullable = false, length = 1)
    private String isManual;

    @Column(name = "PTHQty", precision = 20, scale = 2)
    private BigDecimal pthQty;

    @Column(name = "ETFQuantoQty", precision = 20, scale = 2)
    private BigDecimal etfQuantoQty;

    @Column(name = "CollateralRecallQty", precision = 20, scale = 2)
    private BigDecimal collateralRecallQty;

    @Column(name = "AdjustedQty", precision = 20, scale = 2)
    private BigDecimal adjustedQty;

    @Column(name = "WashQty", precision = 20, scale = 2)
    private BigDecimal washQty;

    @Column(name = "PMID", length = 255)
    private String pmid;

    // One-to-Many relationship with WbOrder
    @OneToMany(mappedBy = "wbShortNeed", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WbOrder> orders;

    // One-to-Many relationship with WbBid
    @OneToMany(mappedBy = "wbShortNeed", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WbBid> bids;
} 