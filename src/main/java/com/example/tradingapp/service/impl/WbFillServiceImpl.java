package com.example.tradingapp.service.impl;

import com.example.tradingapp.dto.WbFillRequestDto;
import com.example.tradingapp.dto.WbFillResponseDto;
import com.example.tradingapp.entity.WbFill;
import com.example.tradingapp.mapper.WbFillMapper;
import com.example.tradingapp.repository.WbFillRepository;
import com.example.tradingapp.service.WbFillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WbFillServiceImpl implements WbFillService {

    private final WbFillRepository wbFillRepository;
    private final WbFillMapper wbFillMapper;

    @Override
    public WbFillResponseDto createWbFill(WbFillRequestDto dto) {
        WbFill wbFill = wbFillMapper.toEntity(dto);
        wbFill = wbFillRepository.save(wbFill);
        return wbFillMapper.toDto(wbFill);
    }

    @Override
    @Transactional(readOnly = true)
    public WbFillResponseDto getWbFillById(Long id) {
        WbFill wbFill = wbFillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("WbFill not found with id: " + id));
        return wbFillMapper.toDto(wbFill);
    }

    @Override
    @Transactional(readOnly = true)
    public WbFillResponseDto getWbFillByTicketId(Long ticketId) {
        WbFill wbFill = wbFillRepository.findByTicketId(ticketId)
                .orElseThrow(() -> new RuntimeException("WbFill not found with ticketId: " + ticketId));
        return wbFillMapper.toDto(wbFill);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WbFillResponseDto> getAllWbFills() {
        List<WbFill> wbFills = wbFillRepository.findAll();
        return wbFillMapper.toDtoList(wbFills);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WbFillResponseDto> getWbFillsByOrderId(Long orderId) {
        List<WbFill> wbFills = wbFillRepository.findByOrderId(orderId);
        return wbFillMapper.toDtoList(wbFills);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WbFillResponseDto> getWbFillsByBidId(String bidId) {
        List<WbFill> wbFills = wbFillRepository.findByBidId(bidId);
        return wbFillMapper.toDtoList(wbFills);
    }

    @Override
    public WbFillResponseDto updateWbFill(Long id, WbFillRequestDto dto) {
        WbFill existingWbFill = wbFillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("WbFill not found with id: " + id));
        
        WbFill updatedWbFill = wbFillMapper.toEntity(dto);
        updatedWbFill.setId(existingWbFill.getId());
        updatedWbFill = wbFillRepository.save(updatedWbFill);
        
        return wbFillMapper.toDto(updatedWbFill);
    }

    @Override
    public void deleteWbFill(Long id) {
        if (!wbFillRepository.existsById(id)) {
            throw new RuntimeException("WbFill not found with id: " + id);
        }
        wbFillRepository.deleteById(id);
    }
} 