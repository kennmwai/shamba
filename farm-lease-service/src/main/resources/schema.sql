-- -- use farm_db;

create table farm_leases (
    id bigint AUTO_INCREMENT PRIMARY KEY,
    farm_id bigint not null,
    lease_tenant varchar(255) not null,
    lease_type varchar(53) not null,
    lease_status varchar(53) not null,
    lease_rent float(53) not null,
    lease_duration varchar(53) not null,
    lease_start varchar(53) not null,
    lease_end varchar(53) not null,
    primary key (id)
);

create table farm_payments (
    id bigint AUTO_INCREMENT PRIMARY KEY,
    payment_status varchar(53) not null,
    payment_amount float(53) not null,
    payment_method varchar(53) not null,
    payment_receipt bigint not null,
    payment_date date not null,
    payment_notes varchar(255),
    lease_id bigint not null,
    FOREIGN KEY (lease_id) REFERENCES farm_leases(id)
);
