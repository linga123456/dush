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
@Table(name = "WB_ORDER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WbOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "OrderID", nullable = false)
    private Long orderId;

    @Column(name = "OrderGroupID", nullable = false)
    private Long orderGroupId;

    @Column(name = "CorrelationID", nullable = false, length = 255)
    private String correlationId;

    @Column(name = "FillQuantity", precision = 20, scale = 2)
    private BigDecimal fillQuantity;

    // Many-to-One relationship with WbOrderGroup
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OrderGroupID", referencedColumnName = "OrderGroupID", insertable = false, updatable = false)
    private WbOrderGroup wbOrderGroup;

    // Many-to-One relationship with WbShortNeed
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CorrelationID", referencedColumnName = "CorrelationID", insertable = false, updatable = false)
    private WbShortNeed wbShortNeed;

    // One-to-Many relationship with WbBid
    @OneToMany(mappedBy = "wbOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WbBid> bids;

    // One-to-Many relationship with WbFill
    @OneToMany(mappedBy = "wbOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WbFill> fills;
} 