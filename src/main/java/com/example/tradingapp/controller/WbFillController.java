package com.example.tradingapp.controller;

import com.example.tradingapp.dto.WbFillRequestDto;
import com.example.tradingapp.dto.WbFillResponseDto;
import com.example.tradingapp.service.WbFillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/wb-fills")
@RequiredArgsConstructor
public class WbFillController {

    private final WbFillService wbFillService;

    @PostMapping
    public ResponseEntity<WbFillResponseDto> createWbFill(@Valid @RequestBody WbFillRequestDto dto) {
        WbFillResponseDto response = wbFillService.createWbFill(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WbFillResponseDto> getWbFillById(@PathVariable Long id) {
        WbFillResponseDto response = wbFillService.getWbFillById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/ticket/{ticketId}")
    public ResponseEntity<WbFillResponseDto> getWbFillByTicketId(@PathVariable Long ticketId) {
        WbFillResponseDto response = wbFillService.getWbFillByTicketId(ticketId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<WbFillResponseDto>> getAllWbFills() {
        List<WbFillResponseDto> response = wbFillService.getAllWbFills();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<WbFillResponseDto>> getWbFillsByOrderId(@PathVariable Long orderId) {
        List<WbFillResponseDto> response = wbFillService.getWbFillsByOrderId(orderId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/bid/{bidId}")
    public ResponseEntity<List<WbFillResponseDto>> getWbFillsByBidId(@PathVariable String bidId) {
        List<WbFillResponseDto> response = wbFillService.getWbFillsByBidId(bidId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WbFillResponseDto> updateWbFill(
            @PathVariable Long id, @Valid @RequestBody WbFillRequestDto dto) {
        WbFillResponseDto response = wbFillService.updateWbFill(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWbFill(@PathVariable Long id) {
        wbFillService.deleteWbFill(id);
        return ResponseEntity.noContent().build();
    }
} 