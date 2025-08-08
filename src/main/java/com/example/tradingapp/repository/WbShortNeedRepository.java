package com.example.tradingapp.repository;

import com.example.tradingapp.entity.WbShortNeed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WbShortNeedRepository extends JpaRepository<WbShortNeed, String> {

    List<WbShortNeed> findBySecurityCode(String securityCode);

    List<WbShortNeed> findByCreatedDate(LocalDate createdDate);

    List<WbShortNeed> findBySettlementDate(String settlementDate);

    List<WbShortNeed> findByNeedType(String needType);
} 