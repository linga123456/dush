package com.example.tradingapp.service.impl;

import com.example.tradingapp.dto.WbBidRequestDto;
import com.example.tradingapp.dto.WbBidResponseDto;
import com.example.tradingapp.entity.WbBid;
import com.example.tradingapp.mapper.WbBidMapper;
import com.example.tradingapp.repository.WbBidRepository;
import com.example.tradingapp.service.WbBidService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WbBidServiceImpl implements WbBidService {

    private final WbBidRepository wbBidRepository;
    private final WbBidMapper wbBidMapper;

    @Override
    public WbBidResponseDto createWbBid(WbBidRequestDto dto) {
        WbBid wbBid = wbBidMapper.toEntity(dto);
        wbBid = wbBidRepository.save(wbBid);
        return wbBidMapper.toDto(wbBid);
    }

    @Override
    @Transactional(readOnly = true)
    public WbBidResponseDto getWbBidById(Long id) {
        WbBid wbBid = wbBidRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("WbBid not found with id: " + id));
        return wbBidMapper.toDto(wbBid);
    }

    @Override
    @Transactional(readOnly = true)
    public WbBidResponseDto getWbBidByBidId(String bidId) {
        WbBid wbBid = wbBidRepository.findByBidId(bidId)
                .orElseThrow(() -> new RuntimeException("WbBid not found with bidId: " + bidId));
        return wbBidMapper.toDto(wbBid);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WbBidResponseDto> getAllWbBids() {
        List<WbBid> wbBids = wbBidRepository.findAll();
        return wbBidMapper.toDtoList(wbBids);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WbBidResponseDto> getWbBidsByOrderId(Long orderId) {
        List<WbBid> wbBids = wbBidRepository.findByOrderId(orderId);
        return wbBidMapper.toDtoList(wbBids);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WbBidResponseDto> getWbBidsByOrderGroupId(Long orderGroupId) {
        List<WbBid> wbBids = wbBidRepository.findByOrderGroupId(orderGroupId);
        return wbBidMapper.toDtoList(wbBids);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WbBidResponseDto> getWbBidsByBatchId(String batchId) {
        List<WbBid> wbBids = wbBidRepository.findByBatchId(batchId);
        return wbBidMapper.toDtoList(wbBids);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WbBidResponseDto> getWbBidsByStatus(String status) {
        List<WbBid> wbBids = wbBidRepository.findByStatus(status);
        return wbBidMapper.toDtoList(wbBids);
    }

    @Override
    public WbBidResponseDto updateWbBid(Long id, WbBidRequestDto dto) {
        WbBid existingWbBid = wbBidRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("WbBid not found with id: " + id));
        
        WbBid updatedWbBid = wbBidMapper.toEntity(dto);
        updatedWbBid.setId(existingWbBid.getId());
        updatedWbBid = wbBidRepository.save(updatedWbBid);
        
        return wbBidMapper.toDto(updatedWbBid);
    }

    @Override
    public void deleteWbBid(Long id) {
        if (!wbBidRepository.existsById(id)) {
            throw new RuntimeException("WbBid not found with id: " + id);
        }
        wbBidRepository.deleteById(id);
    }
} 