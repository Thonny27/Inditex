package com.inditex.test.api.infrastructure.repository;

import com.inditex.test.api.domain.model.Price;
import com.inditex.test.api.infrastructure.entity.PriceEntity;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PriceRepositoryAdapterTest {

        @Mock
        private JpaPriceRepository jpaPriceRepository;

        @InjectMocks
        private PriceRepositoryAdapter priceRepositoryAdapter;

        public PriceRepositoryAdapterTest() {
                MockitoAnnotations.openMocks(this);
        }

        @Test
        void findApplicablePrice_ShouldReturnPrice_WhenEntityExists() {
                Long brandId = 1L;
                Long productId = 1L;
                LocalDateTime applicationDate = LocalDateTime.now();
                PriceEntity mockEntity = new PriceEntity();
                mockEntity.setBrandId(brandId);
                mockEntity.setProductId(productId);

                when(jpaPriceRepository.findApplicablePrices(brandId, productId, applicationDate))
                    .thenReturn(Collections.singletonList(mockEntity));

                Optional<Price> result = priceRepositoryAdapter.findApplicablePrice(brandId, productId, applicationDate);

                assertTrue(result.isPresent());
                assertEquals(brandId, result.get().getBrandId());
                verify(jpaPriceRepository, times(1)).findApplicablePrices(brandId, productId, applicationDate);
        }

        @Test
        void findApplicablePrice_ShouldReturnEmpty_WhenNoEntityExists() {
                Long brandId = 1L;
                Long productId = 1L;
                LocalDateTime applicationDate = LocalDateTime.now();

                when(jpaPriceRepository.findApplicablePrices(brandId, productId, applicationDate))
                    .thenReturn(Collections.emptyList());

                Optional<Price> result = priceRepositoryAdapter.findApplicablePrice(brandId, productId, applicationDate);

                assertFalse(result.isPresent());
                verify(jpaPriceRepository, times(1)).findApplicablePrices(brandId, productId, applicationDate);
        }
}