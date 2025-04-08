package com.inditex.test.api.infrastructure.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriceNotFoundExceptionTest {

        @Test
        void testExceptionMessage() {
                Long brandId = 1L;
                Long productId = 2L;
                String date = "2023-01-01T00:00:00";

                PriceNotFoundException exception = new PriceNotFoundException(brandId, productId, date);

                String expectedMessage = "No price found for brandId=1, productId=2, date=2023-01-01T00:00:00";
                assertEquals(expectedMessage, exception.getMessage());
        }
}