/*
WARNING
DỮ LIỆU CÓ THỂ KHÔNG CHÈN ĐƯỢC DO XUNG ĐỘT KHÓA HOẶC GIÁ TRỊ
*/
-- Insert Users (id provided, so foreign keys will reference these)
INSERT INTO `fastbreakfastdb_demo`.`fbf_user`
    (`id`, `address`, `email`, `name`, `password`, `phone_number`, `username`, `fbf_role`)
VALUES
    (1, '123 Main St', 'john@example.com', 'John Doe', 'password123', '1234567890', 'john_doe', 'FBF_USER'),
    (2, '456 Park Ave', 'jane@example.com', 'Jane Smith', 'pass456', '0987654321', 'jane_smith', 'FBF_USER'),
    (3, 'Sơn Hòa, Phú Yên', 'phucnguyeho@gmail.com', 'Nguyen Hoang Phuc', '$2a$10$SYg7j8HuNv7EoWti7p3tv.jG5FsaStAD27dE/7GhGDyQkIKruPzKm', '0376816194', 'hp', 'FBF_USER');

-- Insert Categories
INSERT INTO `fastbreakfastdb_demo`.`category`
    (`id`, `name`)
VALUES
    (1, 'Bánh mì'),
    (2, 'Phở, bún, miến'),
    (3, 'Xôi, cháo'),
    (4, 'Bánh ngọt & bánh bao'),
    (5, 'Đồ uống');

-- Insert Food items (each food row references a valid category_id)
INSERT INTO `fastbreakfastdb_demo`.`food`
    (`category_id`, `id`, `description`, `image_url`, `name`)
VALUES
    (1, 1, 'Bánh mì thịt nướng ngon tuyệt', 'https://res.cloudinary.com/dwfaw3iit/image/upload/v1746324093/food1_vp36jg.png', 'Bánh mì thịt nướng'),
    (2, 2, 'Phở bò tái chín đậm đà', 'https://res.cloudinary.com/dwfaw3iit/image/upload/v1746324093/food1_vp36jg.png', 'Phở bò tái'),
    (2, 3, 'Bún chả truyền thống Hà Nội', 'https://res.cloudinary.com/dwfaw3iit/image/upload/v1746324093/food1_vp36jg.png', 'Bún chả'),
    (3, 4, 'Xôi gà thơm ngon', 'https://res.cloudinary.com/dwfaw3iit/image/upload/v1746324093/food1_vp36jg.png', 'Xôi gà'),
    (4, 5, 'Bánh bao chả hấp dẫn', 'https://res.cloudinary.com/dwfaw3iit/image/upload/v1746324093/food1_vp36jg.png', 'Bánh bao chả'),
    (5, 6, 'Trà đào mát lạnh, thơm ngon', 'https://res.cloudinary.com/dwfaw3iit/image/upload/v1746324093/food1_vp36jg.png', 'Trà đào');

-- Food 1: Bánh mì thịt nướng
INSERT INTO `fastbreakfastdb_demo`.`food_size`
    (`discount_percentage`, `price`, `stock`, `food_id`, `id`, `size`)
VALUES
    (0,    2.99, 50, 1,  1, 'S'),
    (5.0,  3.99, 40, 1,  2, 'M'),
    (7.5,  4.99, 30, 1,  3, 'L'),
    (10.0, 5.99, 20, 1,  4, 'XL');

-- Food 2: Phở bò tái
INSERT INTO `fastbreakfastdb_demo`.`food_size`
    (`discount_percentage`, `price`, `stock`, `food_id`, `id`, `size`)
VALUES
    (0,    5.99, 50, 2,  5, 'S'),
    (5.0,  6.99, 40, 2,  6, 'M'),
    (7.5,  7.99, 30, 2,  7, 'L'),
    (10.0, 8.99, 20, 2,  8, 'XL');

-- Food 3: Bún chả
INSERT INTO `fastbreakfastdb_demo`.`food_size`
    (`discount_percentage`, `price`, `stock`, `food_id`, `id`, `size`)
VALUES
    (0,    4.99, 50, 3,  9,  'S'),
    (5.0,  5.99, 40, 3,  10, 'M'),
    (7.5,  6.99, 30, 3,  11, 'L'),
    (10.0, 7.99, 20, 3,  12, 'XL');

-- Food 4: Xôi gà
INSERT INTO `fastbreakfastdb_demo`.`food_size`
    (`discount_percentage`, `price`, `stock`, `food_id`, `id`, `size`)
VALUES
    (0,    3.49, 50, 4,  13, 'S'),
    (5.0,  4.49, 40, 4,  14, 'M'),
    (7.0,  5.49, 30, 4,  15, 'L'),
    (10.0, 6.49, 20, 4,  16, 'XL');

-- Food 5: Bánh bao chả
INSERT INTO `fastbreakfastdb_demo`.`food_size`
    (`discount_percentage`, `price`, `stock`, `food_id`, `id`, `size`)
VALUES
    (0,    2.49, 50, 5,  17, 'S'),
    (5.0,  3.49, 40, 5,  18, 'M'),
    (7.5,  4.49, 30, 5,  19, 'L'),
    (10.0, 5.49, 20, 5,  20, 'XL');

-- Food 6: Trà đào
INSERT INTO `fastbreakfastdb_demo`.`food_size`
    (`discount_percentage`, `price`, `stock`, `food_id`, `id`, `size`)
VALUES
    (0,    1.99, 50, 6,  21, 'S'),
    (5.0,  2.49, 40, 6,  22, 'M'),
    (7.5,  2.99, 30, 6,  23, 'L'),
    (10.0, 3.49, 20, 6,  24, 'XL');

-- Insert Carts for Users (each row references a valid fbf_user_id)
INSERT INTO `fastbreakfastdb_demo`.`cart`
    (`fbf_user_id`, `id`)
VALUES
    (1, 1),
    (2, 2),
    (3, 3);

-- Insert Cart Items (each row references an existing cart_id and food_size_id)
INSERT INTO `fastbreakfastdb_demo`.`cart_item`
    (`discount_percentage`, `price`, `quantity`, `cart_id`, `food_size_id`, `id`)
VALUES
    (0.0, 2.99, 1, 1, 1, 1),
    (0.0, 2.99, 1, 2, 1, 2),
    (0.0, 2.99, 3, 3, 1, 3),
    (5.0, 3.99, 2, 3, 2, 4);

-- Insert Discount Codes
INSERT INTO `fastbreakfastdb_demo`.`discount_code`
    (`discount_percentage`, `expiration_date`, `id`, `code`)
VALUES
    (10.0, DATE_ADD(NOW(), INTERVAL 30 DAY), 1, 'DISCOUNT10'),
    (15.0, DATE_ADD(NOW(), INTERVAL 60 DAY), 2, 'SAVE15');

-- Insert Orders (each order must reference an existing fbf_user and discount_code)
INSERT INTO `fastbreakfastdb_demo`.`fbf_order`
    (`discounted_total_price`, `created_at`, `discount_code_id`, `fbf_user_id`, `id`, `address`, `phone_number`)
VALUES
    (25.98, NOW(), 1, 1, 1, '123 Main St', '1234567890'),
    (25.98, NOW(), 1, 2, 2, '456 Park Ave', '0987654321'),
    (25.98, NOW(), 1, 3, 3, 'Sơn Hòa, Phú Yên', '0376816194');

-- Insert Order Items (each order_item references an existing order and food_size)
INSERT INTO `fastbreakfastdb_demo`.`order_item`
    (`discount_percentage`, `discounted_price`, `quantity`, `fbf_order_id`, `food_size_id`, `id`)
VALUES
    (0.0, 2.99, 2, 1, 2, 1),
    (0.0, 2.99, 2, 2, 2, 2),
    (5.0, 2.99, 2, 3, 2, 3);

