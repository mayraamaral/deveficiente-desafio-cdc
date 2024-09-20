CREATE TABLE `coupon` (
    code VARCHAR(100) PRIMARY KEY NOT NULL,
    percentage TINYINT UNSIGNED NOT NULL,
    expiration_date DATE NOT NULL
);