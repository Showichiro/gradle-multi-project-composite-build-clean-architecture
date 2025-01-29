CREATE TABLE IF NOT EXISTS inventories (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS orders (
    id BIGINT PRIMARY KEY,
    customer_info VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    inventory_id BIGINT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (inventory_id) REFERENCES inventories(id)
);
