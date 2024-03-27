-- -- USE farm_db;

INSERT INTO farm_leases (farm_id, lease_tenant, lease_type, lease_Status, lease_rent, lease_duration, lease_start, lease_end)
VALUES (1, 'Quavo', 'Short-term', 'Active', 3500.00, '6 months', '2022-01-10', '2022-07-11');

INSERT INTO farm_leases (farm_id, lease_tenant, lease_type, lease_Status, lease_rent, lease_duration, lease_start, lease_end)
VALUES (2, 'Take Off', 'Long-term', 'Inactive', 7500.00, '12 months', '2022-01-10', '2023-01-11');

INSERT INTO farm_leases (farm_id, lease_tenant, lease_type, lease_Status, lease_rent, lease_duration, lease_start, lease_end)
VALUES (2, 'Off Set', 'Long-term', 'Expired', 1500.00, '24 months', '2020-01-10', '2022-01-11');


-- Insert sample data into payments table
INSERT INTO farm_payments (lease_id, payment_status, payment_amount, payment_method, payment_receipt, payment_date, payment_notes)
VALUES  (1, 'PAID', 500.00, 'BANK', 123456789, '2022-01-10', 'KK'),
        (1, 'PAID', 500.00, 'BANK', 123456789, '2022-02-10', ''),
        (1, 'PAID', 500.00, 'BANK', 123456789, '2022-03-10', 'KM');

INSERT INTO farm_payments (lease_id, payment_status, payment_amount, payment_method, payment_receipt, payment_date, payment_notes)
VALUES  (2, 'PAID', 500.00, 'BANK', 123456789, '2022-03-10', 'KM');

-- INSERT INTO farm_payments (lease_id, amount, payment_date)
-- VALUES  (3, 500.00, '2022-03-01'), (3, 500.00, '2022-04-01');
