INSERT INTO `order` (id, tps, ttc, id_customer, order_date, state_tax, status, card_id, customer_id,
                     shipping_address_id)
VALUES ('ORD001', 5.00, 1380.00, 'USE1518', '2024-08-02', 10.00, 'Completed', 'CAR123', 'USE1000', 'ADR10619'),
       ('ORD002', 5.00, 2070.00, 'USE1037', '2024-10-23', 10.00, 'Completed', 'CAR124', 'USE1037', 'ADR3874');

INSERT INTO `order_items` (id, quantity, sub_total, order_id, product_id)
VALUES ('ORDi001', 1, 1200.00, 'ORD001', 'PRO5'),
       ('ORDi002', 1, 1000.00, 'ORD002', 'PRO1'),
       ('ORDi003', 2, 800.00, 'ORD002', 'PRO8');
