package com.example.tradingapp.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "WB_ORDER_GROUP")
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

    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NumberOfMarketsSelected", nullable = false)
    private Long numberOfMarketsSelected;

    @Column(name = "BidOption", nullable = false, length = 255)
    private String bidOption;

    // One-to-Many relationship with WbOrder
    @OneToMany(mappedBy = "wbOrderGroup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WbOrder> orders;

    // One-to-Many relationship with WbBid
    @OneToMany(mappedBy = "wbOrderGroup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WbBid> bids;
} 