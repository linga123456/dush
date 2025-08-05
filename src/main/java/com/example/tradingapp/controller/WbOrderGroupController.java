package com.example.tradingapp.controller;

import com.example.tradingapp.dto.WbOrderGroupRequestDto;
import com.example.tradingapp.dto.WbOrderGroupResponseDto;
import com.example.tradingapp.service.WbOrderGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/wb-order-groups")
@RequiredArgsConstructor
public class WbOrderGroupController {

    private final WbOrderGroupService wbOrderGroupService;

    @PostMapping
    public ResponseEntity<WbOrderGroupResponseDto> createWbOrderGroup(@Valid @RequestBody WbOrderGroupRequestDto dto) {
        WbOrderGroupResponseDto response = wbOrderGroupService.createWbOrderGroup(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WbOrderGroupResponseDto> getWbOrderGroupById(@PathVariable Long id) {
        WbOrderGroupResponseDto response = wbOrderGroupService.getWbOrderGroupById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/order-group/{orderGroupId}")
    public ResponseEntity<WbOrderGroupResponseDto> getWbOrderGroupByOrderGroupId(@PathVariable Long orderGroupId) {
        WbOrderGroupResponseDto response = wbOrderGroupService.getWbOrderGroupByOrderGroupId(orderGroupId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<WbOrderGroupResponseDto>> getAllWbOrderGroups() {
        List<WbOrderGroupResponseDto> response = wbOrderGroupService.getAllWbOrderGroups();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<WbOrderGroupResponseDto>> getWbOrderGroupsByUserId(@PathVariable String userId) {
        List<WbOrderGroupResponseDto> response = wbOrderGroupService.getWbOrderGroupsByUserId(userId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WbOrderGroupResponseDto> updateWbOrderGroup(
            @PathVariable Long id, @Valid @RequestBody WbOrderGroupRequestDto dto) {
        WbOrderGroupResponseDto response = wbOrderGroupService.updateWbOrderGroup(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWbOrderGroup(@PathVariable Long id) {
        wbOrderGroupService.deleteWbOrderGroup(id);
        return ResponseEntity.noContent().build();
    }
} 