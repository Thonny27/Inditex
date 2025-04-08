package com.inditex.test.api.infrastructure.controller;

import com.inditex.test.api.domain.model.Price;
import com.inditex.test.api.infrastructure.controller.dto.PriceResponse;
import com.inditex.test.api.mapper.PriceMapper;
import com.inditex.test.api.service.PriceService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PriceControllerTest {

        @Mock
        private PriceService priceService;

        @InjectMocks
        private PriceController priceController;

        public PriceControllerTest() {
                MockitoAnnotations.openMocks(this);
        }

        @Test
        void getPrice_ShouldReturnPriceResponse_WhenPriceExists() {
                Long brandId = 1L;
                Long productId = 1L;
                LocalDateTime applicationDate = LocalDateTime.now();
                Price mockPrice = Price.builder()
                    .brandId(brandId)
                    .productId(productId)
                    .priceList(1) // Set priceList to a non-null value
                    .startDate(applicationDate.minusDays(1))
                    .endDate(applicationDate.plusDays(1))
                    .price(100.0)
                    .build();
                PriceResponse expectedResponse = PriceMapper.toResponse(mockPrice);

                when(priceService.getApplicablePrice(brandId, productId, applicationDate))
                    .thenReturn(mockPrice);

                ResponseEntity<PriceResponse> response = priceController.getPrice(brandId, productId, applicationDate);

                assertNotNull(response);
                assertEquals(200, response.getStatusCodeValue());
                assertEquals(expectedResponse, response.getBody());
                verify(priceService, times(1)).getApplicablePrice(brandId, productId, applicationDate);
        }
}