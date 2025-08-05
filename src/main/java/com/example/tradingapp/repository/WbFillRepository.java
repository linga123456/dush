package com.example.tradingapp.repository;

import com.example.tradingapp.entity.WbFill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WbFillRepository extends JpaRepository<WbFill, Long> {

    Optional<WbFill> findByTicketId(Long ticketId);

    List<WbFill> findByOrderId(Long orderId);

    List<WbFill> findByBidId(String bidId);
} 