package com.inditex.test.api.service;

import com.inditex.test.api.domain.model.Price;
import com.inditex.test.api.domain.repository.PriceRepository;
import com.inditex.test.api.infrastructure.exception.PriceNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PriceServiceTest {

        @Mock
        private PriceRepository priceRepository;

        @InjectMocks
        private PriceService priceService;

        public PriceServiceTest() {
                MockitoAnnotations.openMocks(this);
        }

        @Test
        void getApplicablePrice_ShouldReturnPrice_WhenPriceExists() {
                Long brandId = 1L;
                Long productId = 1L;
                LocalDateTime applicationDate = LocalDateTime.now();
                Price mockPrice = Price.builder().brandId(brandId).productId(productId).build();

                when(priceRepository.findApplicablePrice(brandId, productId, applicationDate))
                    .thenReturn(Optional.of(mockPrice));

                Price result = priceService.getApplicablePrice(brandId, productId, applicationDate);

                assertNotNull(result);
                assertEquals(brandId, result.getBrandId());
                verify(priceRepository, times(1)).findApplicablePrice(brandId, productId, applicationDate);
        }

        @Test
        void getApplicablePrice_ShouldThrowException_WhenPriceNotFound() {
                Long brandId = 1L;
                Long productId = 1L;
                LocalDateTime applicationDate = LocalDateTime.now();

                when(priceRepository.findApplicablePrice(brandId, productId, applicationDate))
                    .thenReturn(Optional.empty());

                assertThrows(PriceNotFoundException.class, () ->
                    priceService.getApplicablePrice(brandId, productId, applicationDate));

                verify(priceRepository, times(1)).findApplicablePrice(brandId, productId, applicationDate);
        }
}