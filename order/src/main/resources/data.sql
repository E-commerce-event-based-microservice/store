CREATE TABLE IF NOT EXISTS orders (
                                      order_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      status VARCHAR(255),
    date TIMESTAMP,
    price DOUBLE,
    user_id BIGINT,
    shipping_address_id BIGINT,
    billing_address_id BIGINT
    );

CREATE TABLE IF NOT EXISTS order_items (
                                           item_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                           num INT,
                                           order_id BIGINT,
                                           FOREIGN KEY (order_id) REFERENCES orders(order_id)
    );

INSERT INTO orders (status, date, price, user_id, shipping_address_id, billing_address_id) VALUES
                                                                                               ('Processing', CURRENT_TIMESTAMP, 100.0, 1, 1, 1),
                                                                                               ('Shipped', CURRENT_TIMESTAMP, 200.0, 2, 2, 2),
                                                                                               ('Delivered', CURRENT_TIMESTAMP, 150.0, 3, 3, 3),
                                                                                               ('Cancelled', CURRENT_TIMESTAMP, 120.0, 4, 4, 4),
                                                                                               ('Processing', CURRENT_TIMESTAMP, 180.0, 5, 5, 5),
                                                                                               ('Shipped', CURRENT_TIMESTAMP, 130.0, 6, 6, 6),
                                                                                               ('Delivered', CURRENT_TIMESTAMP, 170.0, 7, 7, 7),
                                                                                               ('Cancelled', CURRENT_TIMESTAMP, 160.0, 8, 8, 8),
                                                                                               ('Processing', CURRENT_TIMESTAMP, 140.0, 9, 9, 9),
                                                                                               ('Shipped', CURRENT_TIMESTAMP, 110.0, 10, 10, 10);

INSERT INTO order_items (num, order_id) VALUES
                                            (1, 1),
                                            (2, 1),
                                            (3, 2),
                                            (4, 2),
                                            (1, 3),
                                            (2, 3),
                                            (3, 4),
                                            (4, 4),
                                            (1, 5),
                                            (2, 5),
                                            (3, 6),
                                            (4, 6),
                                            (1, 7),
                                            (2, 7),
                                            (3, 8),
                                            (4, 8),
                                            (1, 9),
                                            (2, 9),
                                            (3, 10),
                                            (4, 10);