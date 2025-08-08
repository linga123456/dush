package com.example.tradingapp.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "WB_ORDER_GROUP", schema = "APP")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WbOrderGroup {

    @Id
    @Column(name = "OrderGroupID", nullable = false)
    private Long orderGroupId;

    @Column(name = "UserId", nullable = false, length = 255)
    private String userId;

    @Column(name = "ButtonClickTime", nullable = false)
    private LocalDateTime buttonClickTime;

    @Column(name = "NumberOfShortsSelected", nullable = false)
    private Long numberOfShortsSelected;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NumberOfMarketsSelected", nullable = false)
    private Long numberOfMarketsSelected;

    // One-to-Many relationship with WbOrder
    @OneToMany(mappedBy = "wbOrderGroup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WbOrder> orders;

    // One-to-Many relationship with WbBid
    @OneToMany(mappedBy = "wbOrderGroup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WbBid> bids;
} 