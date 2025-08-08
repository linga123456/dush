package com.example.tradingapp.repository;

import com.example.tradingapp.entity.WbOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WbOrderRepository extends JpaRepository<WbOrder, Long> {

    Optional<WbOrder> findByOrderId(Long orderId);

    List<WbOrder> findByOrderGroupId(Long orderGroupId);

    List<WbOrder> findByCorrelationId(String correlationId);
} 