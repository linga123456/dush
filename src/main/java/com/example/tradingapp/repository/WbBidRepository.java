package com.example.tradingapp.repository;

import com.example.tradingapp.entity.WbBid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WbBidRepository extends JpaRepository<WbBid, Long> {

    Optional<WbBid> findByBidId(String bidId);

    List<WbBid> findByOrderId(Long orderId);

    List<WbBid> findByOrderGroupId(Long orderGroupId);

    List<WbBid> findByBatchId(String batchId);

    List<WbBid> findByStatus(String status);
} 