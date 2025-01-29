INSERT INTO inventories (id, name, quantity, created_at, updated_at) VALUES
(1, 'コーヒー豆 100g', 100, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
(2, '紅茶パック 50個入り', 50, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
(3, 'ハーブティー 30個入り', 75, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO orders (id, customer_info, quantity, inventory_id, created_at, updated_at) VALUES
(1, '山田太郎', 2, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
(2, '鈴木花子', 1, 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
(3, '田中一郎', 3, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
