package com.inditex.test.api.service;

import com.inditex.test.api.domain.model.Price;
import com.inditex.test.api.domain.repository.PriceRepository;
import com.inditex.test.api.infrastructure.exception.PriceNotFoundException;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceService {

        private final PriceRepository priceRepository;

        public Price getApplicablePrice(Long brandId, Long productId, LocalDateTime applicationDate) {
                return priceRepository.findApplicablePrice(brandId, productId, applicationDate)
                    .orElseThrow(() -> new PriceNotFoundException(brandId, productId, applicationDate.toString()));
        }
}