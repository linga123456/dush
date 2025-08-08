package com.example.tradingapp.controller;

import com.example.tradingapp.dto.WbBidRequestDto;
import com.example.tradingapp.dto.WbBidResponseDto;
import com.example.tradingapp.service.WbBidService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/wb-bids")
@RequiredArgsConstructor
public class WbBidController {

    private final WbBidService wbBidService;

    @PostMapping
    public ResponseEntity<WbBidResponseDto> createWbBid(@Valid @RequestBody WbBidRequestDto dto) {
        WbBidResponseDto response = wbBidService.createWbBid(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WbBidResponseDto> getWbBidById(@PathVariable Long id) {
        WbBidResponseDto response = wbBidService.getWbBidById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/bid/{bidId}")
    public ResponseEntity<WbBidResponseDto> getWbBidByBidId(@PathVariable String bidId) {
        WbBidResponseDto response = wbBidService.getWbBidByBidId(bidId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<WbBidResponseDto>> getAllWbBids() {
        List<WbBidResponseDto> response = wbBidService.getAllWbBids();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<WbBidResponseDto>> getWbBidsByOrderId(@PathVariable Long orderId) {
        List<WbBidResponseDto> response = wbBidService.getWbBidsByOrderId(orderId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/order-group/{orderGroupId}")
    public ResponseEntity<List<WbBidResponseDto>> getWbBidsByOrderGroupId(@PathVariable Long orderGroupId) {
        List<WbBidResponseDto> response = wbBidService.getWbBidsByOrderGroupId(orderGroupId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/batch/{batchId}")
    public ResponseEntity<List<WbBidResponseDto>> getWbBidsByBatchId(@PathVariable String batchId) {
        List<WbBidResponseDto> response = wbBidService.getWbBidsByBatchId(batchId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<WbBidResponseDto>> getWbBidsByStatus(@PathVariable String status) {
        List<WbBidResponseDto> response = wbBidService.getWbBidsByStatus(status);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WbBidResponseDto> updateWbBid(
            @PathVariable Long id, @Valid @RequestBody WbBidRequestDto dto) {
        WbBidResponseDto response = wbBidService.updateWbBid(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWbBid(@PathVariable Long id) {
        wbBidService.deleteWbBid(id);
        return ResponseEntity.noContent().build();
    }
} 