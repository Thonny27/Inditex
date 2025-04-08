package com.inditex.test.api.infrastructure.controller.dto;

import java.util.Objects;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PriceResponse {
        private Long brandId;
        private Long productId;
        private Integer priceList;
        private String startDate;
        private String endDate;
        private Double price;

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                PriceResponse that = (PriceResponse) o;
                return brandId.equals(that.brandId) &&
                    productId.equals(that.productId) &&
                    priceList.equals(that.priceList) &&
                    startDate.equals(that.startDate) &&
                    endDate.equals(that.endDate) &&
                    price.equals(that.price);
        }

        @Override
        public int hashCode() {
                return Objects.hash(brandId, productId, priceList, startDate, endDate, price);
        }
}