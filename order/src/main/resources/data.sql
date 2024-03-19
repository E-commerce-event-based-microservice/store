CREATE TABLE IF NOT EXISTS orders (
                                      orderId BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      status VARCHAR(255),
    date TIMESTAMP,
    price DOUBLE,
    userId BIGINT,
    shippingAddressId BIGINT,
    billingAddressId BIGINT
    );

CREATE TABLE IF NOT EXISTS product (
                                       productId BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       name VARCHAR(255),
    price DOUBLE,
    description VARCHAR(255),
    stockNumber INT,
    categoryId BIGINT
    );

CREATE TABLE IF NOT EXISTS orderitem (
                                           item_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                           quantity INT,
                                           price FLOAT,
                                           orderId BIGINT,
                                           productId BIGINT,
                                           FOREIGN KEY (orderId) REFERENCES orders(orderId),
    FOREIGN KEY (productId) REFERENCES product(productId)
    );



INSERT INTO orders (status, date, price, userId, shippingAddressId, billingAddressId) VALUES
                                                                                               ('Processing', CURRENT_TIMESTAMP, 25.33, 1, 1, 1),
                                                                                               ('Shipped', CURRENT_TIMESTAMP, 164.4, 2, 2, 2),
                                                                                               ('Delivered', CURRENT_TIMESTAMP, 25.33, 3, 3, 3),
                                                                                               ('Cancelled', CURRENT_TIMESTAMP, 164.4, 4, 4, 4),
                                                                                               ('Processing', CURRENT_TIMESTAMP, 25.33, 5, 5, 5),
                                                                                               ('Shipped', CURRENT_TIMESTAMP, 164.4, 6, 6, 6),
                                                                                               ('Delivered', CURRENT_TIMESTAMP, 25.33, 7, 7, 7),
                                                                                               ('Cancelled', CURRENT_TIMESTAMP, 164.4, 8, 8, 8),
                                                                                               ('Processing', CURRENT_TIMESTAMP, 25.33, 9, 9, 9),
                                                                                               ('Shipped', CURRENT_TIMESTAMP, 164.4, 10, 10, 10);

INSERT INTO product (name, price, description, stockNumber, categoryId) VALUES
                                                                            ('Red Book', 22.99, 'This is a red book!', 60, 1),
                                                                            ('Open Book', 2.34, 'This is an open book!', 60, 1),
                                                                            ('Flower Book', 108.76, 'This is a flower book!', 60, 1),
                                                                            ('Photo Album', 55.64, 'This is a photo album!', 60, 2);

INSERT INTO orderitem (quantity, price, productId, orderId) VALUES
                                            (1, 22.99, 1, 1),
                                            (1, 2.34, 2, 1),
                                            (1, 108.76, 3, 2),
                                            (1, 55.64, 4, 2),
                                            (1, 22.99, 1, 3),
                                            (1, 2.34, 2, 3),
                                            (1, 108.76, 3, 4),
                                            (1, 55.64, 4, 4),
                                            (1, 22.99, 1, 5),
                                            (1, 2.34, 2, 5),
                                            (1, 108.76, 3, 6),
                                            (1, 55.64, 4, 6),
                                            (1, 22.99, 1, 7),
                                            (1, 2.34, 2, 7),
                                            (1, 108.76, 3, 8),
                                            (1, 55.64, 4, 8),
                                            (1, 22.99, 1, 9),
                                            (1, 2.34, 2, 9),
                                            (1, 108.76, 3, 10),
                                            (1, 55.64, 4, 10);

