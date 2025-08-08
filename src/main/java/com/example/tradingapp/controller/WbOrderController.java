package com.example.tradingapp.controller;

import com.example.tradingapp.dto.WbOrderRequestDto;
import com.example.tradingapp.dto.WbOrderResponseDto;
import com.example.tradingapp.service.WbOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/wb-orders")
@RequiredArgsConstructor
public class WbOrderController {

    private final WbOrderService wbOrderService;

    @PostMapping
    public ResponseEntity<WbOrderResponseDto> createWbOrder(@Valid @RequestBody WbOrderRequestDto dto) {
        WbOrderResponseDto response = wbOrderService.createWbOrder(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<WbOrderResponseDto> getWbOrderByOrderId(@PathVariable Long orderId) {
        WbOrderResponseDto response = wbOrderService.getWbOrderByOrderId(orderId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<WbOrderResponseDto>> getAllWbOrders() {
        List<WbOrderResponseDto> response = wbOrderService.getAllWbOrders();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/order-group/{orderGroupId}")
    public ResponseEntity<List<WbOrderResponseDto>> getWbOrdersByOrderGroupId(@PathVariable Long orderGroupId) {
        List<WbOrderResponseDto> response = wbOrderService.getWbOrdersByOrderGroupId(orderGroupId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/correlation/{correlationId}")
    public ResponseEntity<List<WbOrderResponseDto>> getWbOrdersByCorrelationId(@PathVariable String correlationId) {
        List<WbOrderResponseDto> response = wbOrderService.getWbOrdersByCorrelationId(correlationId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/order/{orderId}")
    public ResponseEntity<WbOrderResponseDto> updateWbOrder(
            @PathVariable Long orderId, @Valid @RequestBody WbOrderRequestDto dto) {
        WbOrderResponseDto response = wbOrderService.updateWbOrder(orderId, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/order/{orderId}")
    public ResponseEntity<Void> deleteWbOrder(@PathVariable Long orderId) {
        wbOrderService.deleteWbOrder(orderId);
        return ResponseEntity.noContent().build();
    }
} 