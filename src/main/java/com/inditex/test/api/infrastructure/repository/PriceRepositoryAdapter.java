package com.inditex.test.api.infrastructure.repository;

import com.inditex.test.api.domain.model.Price;
import com.inditex.test.api.domain.repository.PriceRepository;
import com.inditex.test.api.mapper.PriceMapper;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PriceRepositoryAdapter implements PriceRepository {

        private final JpaPriceRepository jpaRepository;

        @Override
        public Optional<Price> findApplicablePrice(Long brandId, Long productId, LocalDateTime applicationDate) {
                return jpaRepository.findApplicablePrices(brandId, productId, applicationDate)
                    .stream()
                    .findFirst()
                    .map(PriceMapper::toDomain);
        }
}