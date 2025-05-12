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
    (1, 1, 'Bánh mì thịt nướng ngon tuyệt', 'https://res.cloudinary.com/dwfaw3iit/image/upload/v1747038004/top-15-hinh-anh-mon-an-ngon-viet-nam-khien-ban-khong-the-roi-mat-1_y5yh10.jpg', 'Bánh mì thịt nướng'),
    (2, 2, 'Phở bò tái chín đậm đà', 'https://res.cloudinary.com/dwfaw3iit/image/upload/v1747038004/top-15-hinh-anh-mon-an-ngon-viet-nam-khien-ban-khong-the-roi-mat-1_y5yh10.jpg', 'Phở bò tái'),
    (2, 3, 'Bún chả truyền thống Hà Nội', 'https://res.cloudinary.com/dwfaw3iit/image/upload/v1747038004/top-15-hinh-anh-mon-an-ngon-viet-nam-khien-ban-khong-the-roi-mat-1_y5yh10.jpg', 'Bún chả'),
    (3, 4, 'Xôi gà thơm ngon', 'https://res.cloudinary.com/dwfaw3iit/image/upload/v1747038004/top-15-hinh-anh-mon-an-ngon-viet-nam-khien-ban-khong-the-roi-mat-1_y5yh10.jpg', 'Xôi gà'),
    (4, 5, 'Bánh bao chả hấp dẫn', 'https://res.cloudinary.com/dwfaw3iit/image/upload/v1747038004/top-15-hinh-anh-mon-an-ngon-viet-nam-khien-ban-khong-the-roi-mat-1_y5yh10.jpg', 'Bánh bao chả'),
    (5, 6, 'Trà đào mát lạnh, thơm ngon', 'https://res.cloudinary.com/dwfaw3iit/image/upload/v1747038004/top-15-hinh-anh-mon-an-ngon-viet-nam-khien-ban-khong-the-roi-mat-1_y5yh10.jpg', 'Trà đào');

-- Food 1: Bánh mì thịt nướng
INSERT INTO `fastbreakfastdb_demo`.`food_size`
    (`discount_percentage`, `price`, `stock`, `food_id`, `id`, `size`)
VALUES
    (0,    10000, 50, 1,  1, 'S'),
    (5.0,  15000, 40, 1,  2, 'M'),
    (7.5,  20000, 30, 1,  3, 'L'),
    (10.0, 25000, 20, 1,  4, 'XL');

-- Food 2: Phở bò tái
INSERT INTO `fastbreakfastdb_demo`.`food_size`
    (`discount_percentage`, `price`, `stock`, `food_id`, `id`, `size`)
VALUES
    (0,    15000, 50, 2,  5, 'S'),
    (5.0,  25000, 40, 2,  6, 'M'),
    (7.5,  30000, 30, 2,  7, 'L'),
    (10.0, 35000, 20, 2,  8, 'XL');

-- Food 3: Bún chả
INSERT INTO `fastbreakfastdb_demo`.`food_size`
    (`discount_percentage`, `price`, `stock`, `food_id`, `id`, `size`)
VALUES
    (0,    15000, 50, 3,  9,  'S'),
    (5.0,  20000, 40, 3,  10, 'M'),
    (7.5,  25000, 30, 3,  11, 'L'),
    (10.0, 30000, 20, 3,  12, 'XL');

-- Food 4: Xôi gà
INSERT INTO `fastbreakfastdb_demo`.`food_size`
    (`discount_percentage`, `price`, `stock`, `food_id`, `id`, `size`)
VALUES
    (0,    12000, 50, 4,  13, 'S'),
    (5.0,  18000, 40, 4,  14, 'M'),
    (7.0,  22000, 30, 4,  15, 'L'),
    (10.0, 27000, 20, 4,  16, 'XL');

-- Food 5: Bánh bao chả
INSERT INTO `fastbreakfastdb_demo`.`food_size`
    (`discount_percentage`, `price`, `stock`, `food_id`, `id`, `size`)
VALUES
    (0,    10000, 50, 5,  17, 'S'),
    (5.0,  12000, 40, 5,  18, 'M'),
    (7.5,  15000, 30, 5,  19, 'L'),
    (10.0, 18000, 20, 5,  20, 'XL');

-- Food 6: Trà đào
INSERT INTO `fastbreakfastdb_demo`.`food_size`
    (`discount_percentage`, `price`, `stock`, `food_id`, `id`, `size`)
VALUES
    (0,    10000, 50, 6,  21, 'S'),
    (5.0,  12000, 40, 6,  22, 'M'),
    (7.5,  15000, 30, 6,  23, 'L'),
    (10.0, 18000, 20, 6,  24, 'XL');


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

