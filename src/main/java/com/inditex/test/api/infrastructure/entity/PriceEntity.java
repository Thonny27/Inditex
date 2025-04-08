package com.inditex.test.api.infrastructure.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "prices")
@Getter
@Setter
public class PriceEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "brand_id")
        private Long brandId;

        @Column(name = "start_date")
        private LocalDateTime startDate;

        @Column(name = "end_date")
        private LocalDateTime endDate;

        @Column(name = "price_list")
        private Integer priceList;

        @Column(name = "product_id")
        private Long productId;

        @Column(name = "priority")
        private Integer priority;

        @Column(name = "price")
        private Double price;

        @Column(name = "currency")
        private String currency;
}
