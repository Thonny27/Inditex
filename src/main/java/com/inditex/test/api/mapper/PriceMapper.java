package com.inditex.test.api.mapper;

import com.inditex.test.api.domain.model.Price;
import com.inditex.test.api.infrastructure.controller.dto.PriceResponse;
import com.inditex.test.api.infrastructure.entity.PriceEntity;

public class PriceMapper {

        public static Price toDomain(PriceEntity entity) {
                return Price.builder()
                    .brandId(entity.getBrandId())
                    .startDate(entity.getStartDate())
                    .endDate(entity.getEndDate())
                    .priceList(entity.getPriceList())
                    .productId(entity.getProductId())
                    .priority(entity.getPriority())
                    .price(entity.getPrice())
                    .currency(entity.getCurrency())
                    .build();
        }

        public static PriceResponse toResponse(Price price) {
                return PriceResponse.builder()
                    .brandId(price.getBrandId())
                    .productId(price.getProductId())
                    .priceList(price.getPriceList())
                    .startDate(String.valueOf(price.getStartDate()))
                    .endDate(String.valueOf(price.getEndDate()))
                    .price(price.getPrice())
                    .build();
        }
}