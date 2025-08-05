package com.example.tradingapp.controller;

import com.example.tradingapp.dto.WbShortNeedRequestDto;
import com.example.tradingapp.dto.WbShortNeedResponseDto;
import com.example.tradingapp.service.WbShortNeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/wb-short-needs")
@RequiredArgsConstructor
public class WbShortNeedController {

    private final WbShortNeedService wbShortNeedService;

    @PostMapping
    public ResponseEntity<WbShortNeedResponseDto> createWbShortNeed(@Valid @RequestBody WbShortNeedRequestDto dto) {
        WbShortNeedResponseDto response = wbShortNeedService.createWbShortNeed(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WbShortNeedResponseDto> getWbShortNeedById(@PathVariable Long id) {
        WbShortNeedResponseDto response = wbShortNeedService.getWbShortNeedById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/composite")
    public ResponseEntity<WbShortNeedResponseDto> getWbShortNeedByCompositeKey(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate createdDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime receiveTime,
            @RequestParam String securityCode,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate settlementDate) {
        WbShortNeedResponseDto response = wbShortNeedService.getWbShortNeedByCompositeKey(
                createdDate, receiveTime, securityCode, settlementDate);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<WbShortNeedResponseDto>> getAllWbShortNeeds() {
        List<WbShortNeedResponseDto> response = wbShortNeedService.getAllWbShortNeeds();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/security-code/{securityCode}")
    public ResponseEntity<List<WbShortNeedResponseDto>> getWbShortNeedsBySecurityCode(@PathVariable String securityCode) {
        List<WbShortNeedResponseDto> response = wbShortNeedService.getWbShortNeedsBySecurityCode(securityCode);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/created-date/{createdDate}")
    public ResponseEntity<List<WbShortNeedResponseDto>> getWbShortNeedsByCreatedDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate createdDate) {
        List<WbShortNeedResponseDto> response = wbShortNeedService.getWbShortNeedsByCreatedDate(createdDate);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/settlement-date/{settlementDate}")
    public ResponseEntity<List<WbShortNeedResponseDto>> getWbShortNeedsBySettlementDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate settlementDate) {
        List<WbShortNeedResponseDto> response = wbShortNeedService.getWbShortNeedsBySettlementDate(settlementDate);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/need-type/{needType}")
    public ResponseEntity<List<WbShortNeedResponseDto>> getWbShortNeedsByNeedType(@PathVariable String needType) {
        List<WbShortNeedResponseDto> response = wbShortNeedService.getWbShortNeedsByNeedType(needType);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WbShortNeedResponseDto> updateWbShortNeed(
            @PathVariable Long id, @Valid @RequestBody WbShortNeedRequestDto dto) {
        WbShortNeedResponseDto response = wbShortNeedService.updateWbShortNeed(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWbShortNeed(@PathVariable Long id) {
        wbShortNeedService.deleteWbShortNeed(id);
        return ResponseEntity.noContent().build();
    }
} 