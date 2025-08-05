package com.example.tradingapp.repository;

import com.example.tradingapp.entity.WbOrderGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WbOrderGroupRepository extends JpaRepository<WbOrderGroup, Long> {

    List<WbOrderGroup> findByUserId(String userId);

    List<WbOrderGroup> findByOrderGroupId(Long orderGroupId);
} 