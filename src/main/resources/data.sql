-- 1. Thêm người dùng
INSERT INTO fbf_user (username, password, email, name, phoneNumber, address) VALUES
('user1', 'password1', 'user1@example.com', 'Alice', '123456789', '123 Street A'),
('user2', 'password2', 'user2@example.com', 'Bob', '987654321', '456 Street B');

-- 2. Thêm kích thước món ăn
INSERT INTO food_size (sizeName) VALUES
('Small'), ('Medium'), ('Large'), ('Extra Large');

-- 3. Thêm danh mục món ăn
INSERT INTO category (name) VALUES
('Beverages'), ('Fast Food'), ('Desserts');

-- 4. Thêm món ăn
INSERT INTO food (name, price, description, imageUrl, food_size_id, category_id) VALUES
('Burger', 5.99, 'Delicious beef burger', 'burger.jpg', 3, 2),
('Pizza', 9.99, 'Cheesy pepperoni pizza', 'pizza.jpg', 4, 2),
('Coca Cola', 1.99, 'Refreshing cola drink', 'cola.jpg', 2, 1);

-- 5. Thêm giỏ hàng cho người dùng
INSERT INTO cart (fbf_user_id) VALUES
(1), (2);

-- 6. Thêm mục giỏ hàng
INSERT INTO cart_item (price, quantity, food_id, cart_id) VALUES
(5.99, 2, 1, 1),  -- 2 Burgers in User 1's cart
(9.99, 1, 2, 2),  -- 1 Pizza in User 2's cart
(1.99, 3, 3, 1);  -- 3 Coca Colas in User 1's cart

-- 7. Thêm đơn hàng
INSERT INTO fbf_order (totalPrice, phoneNumber, address, createdAt, status, fbf_user_id) VALUES
(21.96, '123456789', '123 Street A', NOW(), 'CREATED', 1),
(9.99, '987654321', '456 Street B', NOW(), 'PAID', 2);

-- 8. Thêm mục đơn hàng từ giỏ hàng
INSERT INTO order_item (price, quantity, fbf_order_id, cart_item_id) VALUES
(5.99, 2, 1, 1),
(1.99, 3, 1, 3),
(9.99, 1, 2, 2);

-- 9. Thêm mã giảm giá
INSERT INTO discount_code (code, discountPercentage, expirationDate, expired, order_item_id, fbf_order_id) VALUES
('DISC10', 10.00, '2025-12-31', FALSE, 1, 1),
('DISC20', 20.00, '2025-06-30', FALSE, 2, 2);
