package com.example.tradingapp.service.impl;

import com.example.tradingapp.dto.WbShortNeedRequestDto;
import com.example.tradingapp.dto.WbShortNeedResponseDto;
import com.example.tradingapp.entity.WbShortNeed;
import com.example.tradingapp.mapper.WbShortNeedMapper;
import com.example.tradingapp.repository.WbShortNeedRepository;
import com.example.tradingapp.service.WbShortNeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WbShortNeedServiceImpl implements WbShortNeedService {

    private final WbShortNeedRepository wbShortNeedRepository;
    private final WbShortNeedMapper wbShortNeedMapper;

    @Override
    public WbShortNeedResponseDto createWbShortNeed(WbShortNeedRequestDto dto) {
        WbShortNeed wbShortNeed = wbShortNeedMapper.toEntity(dto);
        wbShortNeed = wbShortNeedRepository.save(wbShortNeed);
        return wbShortNeedMapper.toDto(wbShortNeed);
    }

    @Override
    @Transactional(readOnly = true)
    public WbShortNeedResponseDto getWbShortNeedById(Long id) {
        WbShortNeed wbShortNeed = wbShortNeedRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("WbShortNeed not found with id: " + id));
        return wbShortNeedMapper.toDto(wbShortNeed);
    }

    @Override
    @Transactional(readOnly = true)
    public WbShortNeedResponseDto getWbShortNeedByCompositeKey(LocalDate createdDate, LocalDateTime receiveTime, 
                                                             String securityCode, String settlementDate) {
        WbShortNeed wbShortNeed = wbShortNeedRepository.findByCompositeKey(createdDate, receiveTime, securityCode, settlementDate)
                .orElseThrow(() -> new RuntimeException("WbShortNeed not found with composite key"));
        return wbShortNeedMapper.toDto(wbShortNeed);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WbShortNeedResponseDto> getAllWbShortNeeds() {
        List<WbShortNeed> wbShortNeeds = wbShortNeedRepository.findAll();
        return wbShortNeedMapper.toDtoList(wbShortNeeds);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WbShortNeedResponseDto> getWbShortNeedsBySecurityCode(String securityCode) {
        List<WbShortNeed> wbShortNeeds = wbShortNeedRepository.findBySecurityCode(securityCode);
        return wbShortNeedMapper.toDtoList(wbShortNeeds);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WbShortNeedResponseDto> getWbShortNeedsByCreatedDate(LocalDate createdDate) {
        List<WbShortNeed> wbShortNeeds = wbShortNeedRepository.findByCreatedDate(createdDate);
        return wbShortNeedMapper.toDtoList(wbShortNeeds);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WbShortNeedResponseDto> getWbShortNeedsBySettlementDate(String settlementDate) {
        List<WbShortNeed> wbShortNeeds = wbShortNeedRepository.findBySettlementDate(settlementDate);
        return wbShortNeedMapper.toDtoList(wbShortNeeds);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WbShortNeedResponseDto> getWbShortNeedsByNeedType(String needType) {
        List<WbShortNeed> wbShortNeeds = wbShortNeedRepository.findByNeedType(needType);
        return wbShortNeedMapper.toDtoList(wbShortNeeds);
    }

    @Override
    public WbShortNeedResponseDto updateWbShortNeed(Long id, WbShortNeedRequestDto dto) {
        WbShortNeed existingWbShortNeed = wbShortNeedRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("WbShortNeed not found with id: " + id));
        
        WbShortNeed updatedWbShortNeed = wbShortNeedMapper.toEntity(dto);
        updatedWbShortNeed.setId(existingWbShortNeed.getId());
        updatedWbShortNeed = wbShortNeedRepository.save(updatedWbShortNeed);
        
        return wbShortNeedMapper.toDto(updatedWbShortNeed);
    }

    @Override
    public void deleteWbShortNeed(Long id) {
        if (!wbShortNeedRepository.existsById(id)) {
            throw new RuntimeException("WbShortNeed not found with id: " + id);
        }
        wbShortNeedRepository.deleteById(id);
    }
} 