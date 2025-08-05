package com.example.tradingapp.service.impl;

import com.example.tradingapp.dto.WbOrderGroupRequestDto;
import com.example.tradingapp.dto.WbOrderGroupResponseDto;
import com.example.tradingapp.entity.WbOrderGroup;
import com.example.tradingapp.mapper.WbOrderGroupMapper;
import com.example.tradingapp.repository.WbOrderGroupRepository;
import com.example.tradingapp.service.WbOrderGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WbOrderGroupServiceImpl implements WbOrderGroupService {

    private final WbOrderGroupRepository wbOrderGroupRepository;
    private final WbOrderGroupMapper wbOrderGroupMapper;

    @Override
    public WbOrderGroupResponseDto createWbOrderGroup(WbOrderGroupRequestDto dto) {
        WbOrderGroup wbOrderGroup = wbOrderGroupMapper.toEntity(dto);
        wbOrderGroup = wbOrderGroupRepository.save(wbOrderGroup);
        return wbOrderGroupMapper.toDto(wbOrderGroup);
    }

    @Override
    @Transactional(readOnly = true)
    public WbOrderGroupResponseDto getWbOrderGroupById(Long id) {
        WbOrderGroup wbOrderGroup = wbOrderGroupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("WbOrderGroup not found with id: " + id));
        return wbOrderGroupMapper.toDto(wbOrderGroup);
    }

    @Override
    @Transactional(readOnly = true)
    public WbOrderGroupResponseDto getWbOrderGroupByOrderGroupId(Long orderGroupId) {
        List<WbOrderGroup> wbOrderGroups = wbOrderGroupRepository.findByOrderGroupId(orderGroupId);
        if (wbOrderGroups.isEmpty()) {
            throw new RuntimeException("WbOrderGroup not found with orderGroupId: " + orderGroupId);
        }
        return wbOrderGroupMapper.toDto(wbOrderGroups.get(0));
    }

    @Override
    @Transactional(readOnly = true)
    public List<WbOrderGroupResponseDto> getAllWbOrderGroups() {
        List<WbOrderGroup> wbOrderGroups = wbOrderGroupRepository.findAll();
        return wbOrderGroupMapper.toDtoList(wbOrderGroups);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WbOrderGroupResponseDto> getWbOrderGroupsByUserId(String userId) {
        List<WbOrderGroup> wbOrderGroups = wbOrderGroupRepository.findByUserId(userId);
        return wbOrderGroupMapper.toDtoList(wbOrderGroups);
    }

    @Override
    public WbOrderGroupResponseDto updateWbOrderGroup(Long id, WbOrderGroupRequestDto dto) {
        WbOrderGroup existingWbOrderGroup = wbOrderGroupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("WbOrderGroup not found with id: " + id));
        
        WbOrderGroup updatedWbOrderGroup = wbOrderGroupMapper.toEntity(dto);
        updatedWbOrderGroup.setId(existingWbOrderGroup.getId());
        updatedWbOrderGroup = wbOrderGroupRepository.save(updatedWbOrderGroup);
        
        return wbOrderGroupMapper.toDto(updatedWbOrderGroup);
    }

    @Override
    public void deleteWbOrderGroup(Long id) {
        if (!wbOrderGroupRepository.existsById(id)) {
            throw new RuntimeException("WbOrderGroup not found with id: " + id);
        }
        wbOrderGroupRepository.deleteById(id);
    }
} 