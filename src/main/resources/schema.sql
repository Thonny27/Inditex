CREATE TABLE prices (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        brand_id BIGINT,
                        start_date TIMESTAMP,
                        end_date TIMESTAMP,
                        price_list INT,
                        product_id BIGINT,
                        priority INT,
                        price DOUBLE,
                        currency VARCHAR(10)
);