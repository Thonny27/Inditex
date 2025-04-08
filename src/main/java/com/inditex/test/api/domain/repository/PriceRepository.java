package com.inditex.test.api.domain.repository;

import com.inditex.test.api.domain.model.Price;
import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository {
        Optional<Price> findApplicablePrice(Long brandId, Long productId, LocalDateTime applicationDate);
}