package com.example.tradingapp.repository;

import com.example.tradingapp.entity.WbShortNeed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface WbShortNeedRepository extends JpaRepository<WbShortNeed, Long> {

    @Query("SELECT w FROM WbShortNeed w WHERE w.createdDate = :createdDate AND w.receiveTime = :receiveTime AND w.securityCode = :securityCode AND w.settlementDate = :settlementDate")
    Optional<WbShortNeed> findByCompositeKey(
            @Param("createdDate") LocalDate createdDate,
            @Param("receiveTime") LocalDateTime receiveTime,
            @Param("securityCode") String securityCode,
            @Param("settlementDate") String settlementDate
    );

    List<WbShortNeed> findBySecurityCode(String securityCode);

    List<WbShortNeed> findByCreatedDate(LocalDate createdDate);

    List<WbShortNeed> findBySettlementDate(String settlementDate);

    List<WbShortNeed> findByNeedType(String needType);
} 