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
@Table(name = "WB_BID", schema = "APP")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WbBid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "BidID", nullable = false, length = 255)
    private String bidId;

    @Column(name = "BatchID", nullable = false, length = 255)
    private String batchId;

    @Column(name = "RequestSequence")
    private Long requestSequence;

    @Column(name = "BidType", length = 255)
    private String bidType;

    @Column(name = "NgtChainID")
    private Long ngtChainId;

    @Column(name = "EquilendID")
    private Long equilendId;

    @Column(name = "QuantityFilled", precision = 10, scale = 2)
    private BigDecimal quantityFilled;

    @Column(name = "Status", length = 255)
    private String status;

    @Column(name = "StatusDescription", length = 255)
    private String statusDescription;

    @Column(name = "CptyLeID", nullable = false)
    private Long cptyLeId;

    @Column(name = "CptyCorpID", nullable = false)
    private Long cptyCorpId;

    @Column(name = "LenderReferenceID", nullable = false, length = 255)
    private String lenderReferenceId;

    @Column(name = "Rate", nullable = false, precision = 10, scale = 2)
    private BigDecimal rate;

    @Column(name = "Fee", nullable = false, precision = 10, scale = 2)
    private BigDecimal fee;

    @Column(name = "SubAcct", length = 255)
    private String subAcct;

    @Column(name = "ContractPrice", precision = 10, scale = 2)
    private BigDecimal contractPrice;

    @Column(name = "CollCashAmt", precision = 10, scale = 2)
    private BigDecimal collCashAmt;

    @Column(name = "CollCcy", length = 255)
    private String collCcy;

    @Column(name = "CollType", nullable = false, length = 255)
    private String collType;

    @Column(name = "CollDesc", length = 255)
    private String collDesc;

    @Column(name = "TraderName", nullable = false, length = 255)
    private String traderName;

    @Column(name = "StartTime", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "EndTime", nullable = false)
    private LocalDateTime endTime;

    @Column(name = "AbSeqNbr", nullable = false)
    private Long abSeqNbr;

    @Column(name = "DateStamp", nullable = false)
    private LocalDateTime dateStamp;

    @Column(name = "OrderID", nullable = false)
    private Long orderId;

    @Column(name = "OrderGroupID", nullable = false)
    private Long orderGroupId;

    @Column(name = "CreatedDate", nullable = false)
    private LocalDate createdDate;

    @Column(name = "ReceiveTime", nullable = false)
    private LocalDateTime receiveTime;

    @Column(name = "SecurityCode", nullable = false, length = 255)
    private String securityCode;

    @Column(name = "SettlementDate", nullable = false)
    private String settlementDate;

    // Many-to-One relationship with WbOrder
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OrderID", referencedColumnName = "OrderID", insertable = false, updatable = false)
    private WbOrder wbOrder;

    // Many-to-One relationship with WbOrderGroup
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OrderGroupID", referencedColumnName = "OrderGroupID", insertable = false, updatable = false)
    private WbOrderGroup wbOrderGroup;

    // Many-to-One relationship with WbShortNeed
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "CreatedDate", referencedColumnName = "CreatedDate", insertable = false, updatable = false),
        @JoinColumn(name = "ReceiveTime", referencedColumnName = "ReceiveTime", insertable = false, updatable = false),
        @JoinColumn(name = "SecurityCode", referencedColumnName = "SecurityCode", insertable = false, updatable = false),
        @JoinColumn(name = "SettlementDate", referencedColumnName = "SettlementDate", insertable = false, updatable = false)
    })
    private WbShortNeed wbShortNeed;

    // One-to-Many relationship with WbFill
    @OneToMany(mappedBy = "wbBid", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WbFill> fills;
} 