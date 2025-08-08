package com.example.tradingapp.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "WB_SHORT_NEED", schema = "APP")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WbShortNeed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CreatedDate", nullable = false)
    private LocalDate createdDate;

    @Column(name = "ReceiveTime", nullable = false)
    private LocalDateTime receiveTime;

    @Column(name = "SecurityCode", nullable = false, length = 255)
    private String securityCode;

    @Column(name = "SettlementDate", nullable = false)
    private String settlementDate;

    @Column(name = "NeedType", nullable = false, length = 255)
    private String needType;

    @Column(name = "RunTime", nullable = false)
    private String runTime;

    @Column(name = "PartialFlag", nullable = false, length = 1)
    private String partialFlag;

    @Column(name = "IsNewVersion", nullable = false, length = 1)
    private String isNewVersion;

    @Column(name = "Quantity", nullable = false, precision = 10, scale = 2)
    private BigDecimal quantity;

    @Column(name = "DivStrategy", nullable = false, length = 255)
    private String divStrategy;

    @Column(name = "IsManual", nullable = false, length = 1)
    private String isManual;

    // One-to-Many relationship with WbOrder
    @OneToMany(mappedBy = "wbShortNeed", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WbOrder> orders;

    // One-to-Many relationship with WbBid
    @OneToMany(mappedBy = "wbShortNeed", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WbBid> bids;
} 