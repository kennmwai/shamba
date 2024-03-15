CREATE TABLE farm_leases (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    farmer_name VARCHAR(255) NOT NULL,
    farm_address VARCHAR(255) NOT NULL,
    crop VARCHAR(255) NOT NULL,
    acres INTEGER NOT NULL,
    price_per_acre DOUBLE NOT NULL
);