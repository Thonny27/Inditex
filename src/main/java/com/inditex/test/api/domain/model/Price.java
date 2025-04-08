package com.inditex.test.api.domain.model;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Price {
        private Long brandId;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private Integer priceList;
        private Long productId;
        private Integer priority;
        private Double price;
        private String currency;
}