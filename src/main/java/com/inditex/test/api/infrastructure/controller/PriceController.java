package com.inditex.test.api.infrastructure.controller;

import com.inditex.test.api.infrastructure.controller.dto.PriceResponse;
import com.inditex.test.api.mapper.PriceMapper;
import com.inditex.test.api.service.PriceService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/prices")
@RequiredArgsConstructor
public class PriceController {

        private final PriceService priceService;

        @GetMapping
        public ResponseEntity<PriceResponse> getPrice(
            @RequestParam Long brandId,
            @RequestParam(required = true) Long productId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate) {
                return ResponseEntity.ok(PriceMapper.toResponse(
                    priceService.getApplicablePrice(brandId, productId, applicationDate)));
        }
}
