package com.inditex.test.api.infrastructure.exception;

public class PriceNotFoundException extends RuntimeException {
        public PriceNotFoundException(Long brandId, Long productId, String date) {
                super("No price found for brandId=" + brandId + ", productId=" + productId + ", date=" + date);
        }
}