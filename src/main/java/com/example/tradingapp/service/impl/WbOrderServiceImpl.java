package com.example.tradingapp.service.impl;

import com.example.tradingapp.dto.WbOrderRequestDto;
import com.example.tradingapp.dto.WbOrderResponseDto;
import com.example.tradingapp.entity.WbOrder;
import com.example.tradingapp.mapper.WbOrderMapper;
import com.example.tradingapp.repository.WbOrderRepository;
import com.example.tradingapp.service.WbOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WbOrderServiceImpl implements WbOrderService {

    private final WbOrderRepository wbOrderRepository;
    private final WbOrderMapper wbOrderMapper;

    @Override
    public WbOrderResponseDto createWbOrder(WbOrderRequestDto dto) {
        WbOrder wbOrder = wbOrderMapper.toEntity(dto);
        wbOrder = wbOrderRepository.save(wbOrder);
        return wbOrderMapper.toDto(wbOrder);
    }

    @Override
    @Transactional(readOnly = true)
    public WbOrderResponseDto getWbOrderById(Long id) {
        WbOrder wbOrder = wbOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("WbOrder not found with id: " + id));
        return wbOrderMapper.toDto(wbOrder);
    }

    @Override
    @Transactional(readOnly = true)
    public WbOrderResponseDto getWbOrderByOrderId(Long orderId) {
        WbOrder wbOrder = wbOrderRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("WbOrder not found with orderId: " + orderId));
        return wbOrderMapper.toDto(wbOrder);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WbOrderResponseDto> getAllWbOrders() {
        List<WbOrder> wbOrders = wbOrderRepository.findAll();
        return wbOrderMapper.toDtoList(wbOrders);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WbOrderResponseDto> getWbOrdersByOrderGroupId(Long orderGroupId) {
        List<WbOrder> wbOrders = wbOrderRepository.findByOrderGroupId(orderGroupId);
        return wbOrderMapper.toDtoList(wbOrders);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WbOrderResponseDto> getWbOrdersBySecurityCode(String securityCode) {
        List<WbOrder> wbOrders = wbOrderRepository.findBySecurityCode(securityCode);
        return wbOrderMapper.toDtoList(wbOrders);
    }

    @Override
    public WbOrderResponseDto updateWbOrder(Long id, WbOrderRequestDto dto) {
        WbOrder existingWbOrder = wbOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("WbOrder not found with id: " + id));
        
        WbOrder updatedWbOrder = wbOrderMapper.toEntity(dto);
        updatedWbOrder.setId(existingWbOrder.getId());
        updatedWbOrder = wbOrderRepository.save(updatedWbOrder);
        
        return wbOrderMapper.toDto(updatedWbOrder);
    }

    @Override
    public void deleteWbOrder(Long id) {
        if (!wbOrderRepository.existsById(id)) {
            throw new RuntimeException("WbOrder not found with id: " + id);
        }
        wbOrderRepository.deleteById(id);
    }
} 