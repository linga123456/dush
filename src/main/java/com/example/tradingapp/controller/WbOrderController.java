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

    @GetMapping("/{id}")
    public ResponseEntity<WbOrderResponseDto> getWbOrderById(@PathVariable Long id) {
        WbOrderResponseDto response = wbOrderService.getWbOrderById(id);
        return ResponseEntity.ok(response);
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

    @GetMapping("/security-code/{securityCode}")
    public ResponseEntity<List<WbOrderResponseDto>> getWbOrdersBySecurityCode(@PathVariable String securityCode) {
        List<WbOrderResponseDto> response = wbOrderService.getWbOrdersBySecurityCode(securityCode);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WbOrderResponseDto> updateWbOrder(
            @PathVariable Long id, @Valid @RequestBody WbOrderRequestDto dto) {
        WbOrderResponseDto response = wbOrderService.updateWbOrder(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWbOrder(@PathVariable Long id) {
        wbOrderService.deleteWbOrder(id);
        return ResponseEntity.noContent().build();
    }
} 