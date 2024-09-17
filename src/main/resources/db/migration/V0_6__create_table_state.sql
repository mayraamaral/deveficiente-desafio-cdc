CREATE TABLE `state` (
    state_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL,
    country_id BIGINT NOT NULL,
    CONSTRAINT country_fk FOREIGN KEY (country_id) REFERENCES country(country_id)
);