package com.example.tradingapp.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "WB_FILL", schema = "APP")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WbFill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TicketID", nullable = false)
    private Long ticketId;

    @Column(name = "OrderID", nullable = false)
    private Long orderId;

    @Column(name = "BidID", nullable = false, length = 255)
    private String bidId;

    // Many-to-One relationship with WbOrder
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OrderID", referencedColumnName = "OrderID", insertable = false, updatable = false)
    private WbOrder wbOrder;

    // Many-to-One relationship with WbBid
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BidID", referencedColumnName = "BidID", insertable = false, updatable = false)
    private WbBid wbBid;
} 