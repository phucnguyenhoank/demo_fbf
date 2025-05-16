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
    (1, 'Snacks'),
    (2, 'Meal'),
    (3, 'Vegan'),
    (4, 'Dessert'),
    (5, 'Drink');

-- Insert Food items (each food row references a valid category_id)
INSERT INTO `fastbreakfastdb_demo`.`food`
    (`category_id`, `id`, `description`, `image_url`, `name`)
VALUES
    (1, 1, 'Bánh đa kê giòn rụm, ngọt bùi từ đậu xanh và vừng', 'https://res.cloudinary.com/dwfaw3iit/image/upload/v1747297915/banh-da-ke-v1_ewcb0x.jpg', 'Bánh đa kê'),
    (1, 2, 'Bánh tráng nướng giòn, thơm béo, topping đa dạng', 'https://res.cloudinary.com/dwfaw3iit/image/upload/v1747297913/banh-trang-nuong-v1_ai2li6.jpg', 'Bánh tráng nướng'),
    (1, 3, 'Bánh tẻ mềm mịn, nhân thịt mộc nhĩ đậm đà', 'https://res.cloudinary.com/dwfaw3iit/image/upload/v1747297912/banh-te-v1_xqdyei.webp', 'Bánh tẻ'),
    (1, 4, 'Bánh nậm mềm thơm, nhân tôm thịt hấp dẫn', 'https://res.cloudinary.com/dwfaw3iit/image/upload/v1747297912/banh-nam-v1_abox8e.jpg', 'Bánh nậm'),
    (1, 5, 'Bánh giò nóng hổi, nhân thịt mộc nhĩ béo ngậy', 'https://res.cloudinary.com/dwfaw3iit/image/upload/v1747297912/banh-gio-v1_kyifau.jpg', 'Bánh giò'),
    (2, 6, 'Xôi gà nóng hổi, thịt mềm đậm vị', 'https://res.cloudinary.com/dwfaw3iit/image/upload/v1747297915/xoi_ga_v1_etsbbk.jpg', 'Xôi gà'),
    (2, 7, 'Phở bò tái mềm, nước dùng ngọt thanh', 'https://res.cloudinary.com/dwfaw3iit/image/upload/v1747297915/pho-bo-tai-v1_wgueah.jpg', 'Phở bò tái'),
    (2, 8, 'Bánh mì giòn tan, nhân đậm đà', 'https://res.cloudinary.com/dwfaw3iit/image/upload/v1747297915/banh-mi-v1_h4y4da.jpg', 'Bánh mì'),
    (2, 9, 'Bún chả nướng thơm lừng, nước mắm đậm đà', 'https://res.cloudinary.com/dwfaw3iit/image/upload/v1747297915/bun-cha-v1_dtes9r.jpg', 'Bún chả'),
    (2, 10, 'Bánh bao nóng hổi, nhân thịt trứng thơm ngon', 'https://res.cloudinary.com/dwfaw3iit/image/upload/v1747297914/banh-bao_v1_xdxopw.webp', 'Bánh bao'),
    (3, 11, 'Phở chay thanh đạm, đầy đủ dinh dưỡng', 'https://res.cloudinary.com/dwfaw3iit/image/upload/v1747297916/pho-chay-v1_fc6oha.jpg', 'Phở chay'),
    (3, 12, 'Xôi đậu xanh bùi béo, dẻo thơm', 'https://res.cloudinary.com/dwfaw3iit/image/upload/v1747297916/xoi-dau-xanh-v1_yq597i.jpg', 'Xôi đậu xanh'),
    (3, 13, 'Bún chay nhẹ nhàng, thanh mát cho buổi sáng', 'https://res.cloudinary.com/dwfaw3iit/image/upload/v1747297914/bun-chay-v1_sq93w9.jpg', 'Bún chay'),
    (3, 14, 'Bánh xèo chay giòn rụm, rau sống tươi ngon', 'https://res.cloudinary.com/dwfaw3iit/image/upload/v1747297914/banh-xeo-chay-v1_zlyzlk.jpg', 'Bánh xèo chay'),
    (3, 15, 'Bánh mì chay đậm vị, thích hợp cho ngày rằm', 'https://res.cloudinary.com/dwfaw3iit/image/upload/v1747297913/banh-mi-chay_uve4pl.webp', 'Bánh mì chay'),
    (4, 16, 'Sữa chua nếp cẩm mát lạnh, bổ dưỡng', 'https://res.cloudinary.com/dwfaw3iit/image/upload/v1747297913/sua-chua-nep-cam-v1_cxwuoi.jpg', 'Sữa chua nếp cẩm'),
    (4, 17, 'Chè đậu đen ngọt dịu, mát lành', 'https://res.cloudinary.com/dwfaw3iit/image/upload/v1747297912/che-dau-den-v1_ygqdet.jpg', 'Chè đậu đen'),
    (4, 18, 'Chuối nếp nướng thơm lừng, vị béo ngậy', 'https://res.cloudinary.com/dwfaw3iit/image/upload/v1747297912/chuoi-nep-nuong-v1_wzzn3d.jpg', 'Chuối nếp nướng'),
    (4, 19, 'Kem xôi dẻo thơm, mát lạnh ngày hè', 'https://res.cloudinary.com/dwfaw3iit/image/upload/v1747297911/kem-xoi-v1_nfnshv.webp', 'Kem xôi'),
    (4, 20, 'Bánh flan mềm mịn, ngọt nhẹ', 'https://res.cloudinary.com/dwfaw3iit/image/upload/v1747297911/banh-flan-v1_pwr3ya.webp', 'Bánh flan'),
    (5, 21, 'Nước mía mát lạnh, ngọt tự nhiên', 'https://res.cloudinary.com/dwfaw3iit/image/upload/v1747297914/nuoc-mia-v1_lqhvob.jpg', 'Nước mía'),
    (5, 22, 'Trà đá mát lạnh, giải khát hiệu quả', 'https://res.cloudinary.com/dwfaw3iit/image/upload/v1747297914/tra-da-v1_byfpmo.jpg', 'Trà đá'),
    (5, 23, 'Sữa đậu nành thơm béo, tốt cho sức khỏe', 'https://res.cloudinary.com/dwfaw3iit/image/upload/v1747297913/sua-dau-nanh-v1_pq37yo.jpg', 'Sữa đậu nành'),
    (5, 24, 'Sinh tố cà rốt giàu vitamin, mát lành', 'https://res.cloudinary.com/dwfaw3iit/image/upload/v1747297913/sinh-to-ca-rot-v1_rwxue4.jpg', 'Sinh tố cà rốt'),
    (5, 25, 'Cà phê sữa đá đậm đà, thơm ngát', 'https://res.cloudinary.com/dwfaw3iit/image/upload/v1747297912/ca-phe-sua-da-v1_pgregg.jpg', 'Cà phê sữa đá');


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

-- Food 7: Phở bò tái
INSERT INTO `fastbreakfastdb_demo`.`food_size`
    (`discount_percentage`, `price`, `stock`, `food_id`, `id`, `size`)
VALUES
    (0,     15000, 50, 7,  25, 'S'),
    (5.0,   25000, 40, 7,  26, 'M'),
    (7.5,   30000, 30, 7,  27, 'L'),
    (10.0,  35000, 20, 7,  28, 'XL');

-- Food 8: Bánh mì
INSERT INTO `fastbreakfastdb_demo`.`food_size`
    (`discount_percentage`, `price`, `stock`, `food_id`, `id`, `size`)
VALUES
    (0,     10000, 50, 8,  29, 'S'),
    (5.0,   15000, 40, 8,  30, 'M'),
    (7.5,   20000, 30, 8,  31, 'L'),
    (10.0,  25000, 20, 8,  32, 'XL');

-- Food 9: Bún chả
INSERT INTO `fastbreakfastdb_demo`.`food_size`
    (`discount_percentage`, `price`, `stock`, `food_id`, `id`, `size`)
VALUES
    (0,     15000, 50, 9,  33, 'S'),
    (5.0,   20000, 40, 9,  34, 'M'),
    (7.5,   25000, 30, 9,  35, 'L'),
    (10.0,  30000, 20, 9,  36, 'XL');

-- Food 10: Bánh bao
INSERT INTO `fastbreakfastdb_demo`.`food_size`
    (`discount_percentage`, `price`, `stock`, `food_id`, `id`, `size`)
VALUES
    (0,     10000, 50, 10, 37, 'S'),
    (5.0,   12000, 40, 10, 38, 'M'),
    (7.5,   15000, 30, 10, 39, 'L'),
    (10.0,  18000, 20, 10, 40, 'XL');

-- Food 11: Phở chay
INSERT INTO `fastbreakfastdb_demo`.`food_size`
    (`discount_percentage`, `price`, `stock`, `food_id`, `id`, `size`)
VALUES
    (0,     12000, 50, 11, 41, 'S'),
    (5.0,   18000, 40, 11, 42, 'M'),
    (7.5,   22000, 30, 11, 43, 'L'),
    (10.0,  27000, 20, 11, 44, 'XL');

-- Food 12: Xôi đậu xanh
INSERT INTO `fastbreakfastdb_demo`.`food_size`
    (`discount_percentage`, `price`, `stock`, `food_id`, `id`, `size`)
VALUES
    (0,     12000, 50, 12, 45, 'S'),
    (5.0,   18000, 40, 12, 46, 'M'),
    (7.5,   22000, 30, 12, 47, 'L'),
    (10.0,  27000, 20, 12, 48, 'XL');

-- Food 13: Bún chay
INSERT INTO `fastbreakfastdb_demo`.`food_size`
    (`discount_percentage`, `price`, `stock`, `food_id`, `id`, `size`)
VALUES
    (0,     12000, 50, 13, 49, 'S'),
    (5.0,   18000, 40, 13, 50, 'M'),
    (7.5,   22000, 30, 13, 51, 'L'),
    (10.0,  27000, 20, 13, 52, 'XL');

-- Food 14: Bánh xèo chay
INSERT INTO `fastbreakfastdb_demo`.`food_size`
    (`discount_percentage`, `price`, `stock`, `food_id`, `id`, `size`)
VALUES
    (0,     12000, 50, 14, 53, 'S'),
    (5.0,   18000, 40, 14, 54, 'M'),
    (7.5,   22000, 30, 14, 55, 'L'),
    (10.0,  27000, 20, 14, 56, 'XL');

-- Food 15: Bánh mì chay
INSERT INTO `fastbreakfastdb_demo`.`food_size`
    (`discount_percentage`, `price`, `stock`, `food_id`, `id`, `size`)
VALUES
    (0,     10000, 50, 15, 57, 'S'),
    (5.0,   15000, 40, 15, 58, 'M'),
    (7.5,   20000, 30, 15, 59, 'L'),
    (10.0,  25000, 20, 15, 60, 'XL');

-- Food 16: Sữa chua nếp cẩm
INSERT INTO `fastbreakfastdb_demo`.`food_size`
    (`discount_percentage`, `price`, `stock`, `food_id`, `id`, `size`)
VALUES
    (0,     10000, 50, 16, 61, 'S'),
    (5.0,   15000, 40, 16, 62, 'M'),
    (7.5,   20000, 30, 16, 63, 'L'),
    (10.0,  25000, 20, 16, 64, 'XL');

-- Food 17: Chè đậu đen
INSERT INTO `fastbreakfastdb_demo`.`food_size`
    (`discount_percentage`, `price`, `stock`, `food_id`, `id`, `size`)
VALUES
    (0,     10000, 50, 17, 65, 'S'),
    (5.0,   15000, 40, 17, 66, 'M'),
    (7.5,   20000, 30, 17, 67, 'L'),
    (10.0,  25000, 20, 17, 68, 'XL');

-- Food 18: Chuối nếp nướng
INSERT INTO `fastbreakfastdb_demo`.`food_size`
    (`discount_percentage`, `price`, `stock`, `food_id`, `id`, `size`)
VALUES
    (0,     10000, 50, 18, 69, 'S'),
    (5.0,   15000, 40, 18, 70, 'M'),
    (7.5,   20000, 30, 18, 71, 'L'),
    (10.0,  25000, 20, 18, 72, 'XL');

-- Food 19: Kem xôi
INSERT INTO `fastbreakfastdb_demo`.`food_size`
    (`discount_percentage`, `price`, `stock`, `food_id`, `id`, `size`)
VALUES
    (0,     12000, 50, 19, 73, 'S'),
    (5.0,   18000, 40, 19, 74, 'M'),
    (7.5,   22000, 30, 19, 75, 'L'),
    (10.0,  27000, 20, 19, 76, 'XL');

-- Food 20: Bánh flan
INSERT INTO `fastbreakfastdb_demo`.`food_size`
    (`discount_percentage`, `price`, `stock`, `food_id`, `id`, `size`)
VALUES
    (0,     10000, 50, 20, 77, 'S'),
    (5.0,   15000, 40, 20, 78, 'M'),
    (7.5,   20000, 30, 20, 79, 'L'),
    (10.0,  25000, 20, 20, 80, 'XL');

-- Food 21: Nước mía
INSERT INTO `fastbreakfastdb_demo`.`food_size`
    (`discount_percentage`, `price`, `stock`, `food_id`, `id`, `size`)
VALUES
    (0,      8000, 50, 21, 81, 'S'),
    (5.0,  12000, 40, 21, 82, 'M'),
    (7.5,  15000, 30, 21, 83, 'L'),
    (10.0, 20000, 20, 21, 84, 'XL');

-- Food 22: Trà đá
INSERT INTO `fastbreakfastdb_demo`.`food_size`
    (`discount_percentage`, `price`, `stock`, `food_id`, `id`, `size`)
VALUES
    (0,      5000, 50, 22, 85, 'S'),
    (5.0,   8000, 40, 22, 86, 'M'),
    (7.5,  12000, 30, 22, 87, 'L'),
    (10.0, 15000, 20, 22, 88, 'XL');

-- Food 23: Sữa đậu nành
INSERT INTO `fastbreakfastdb_demo`.`food_size`
    (`discount_percentage`, `price`, `stock`, `food_id`, `id`, `size`)
VALUES
    (0,     8000, 50, 23, 89, 'S'),
    (5.0,  12000, 40, 23, 90, 'M'),
    (7.5,  15000, 30, 23, 91, 'L'),
    (10.0, 20000, 20, 23, 92, 'XL');

-- Food 24: Sinh tố cà rốt
INSERT INTO `fastbreakfastdb_demo`.`food_size`
    (`discount_percentage`, `price`, `stock`, `food_id`, `id`, `size`)
VALUES
    (0,     12000, 50, 24, 93, 'S'),
    (5.0,   18000, 40, 24, 94, 'M'),
    (7.5,   22000, 30, 24, 95, 'L'),
    (10.0,  27000, 20, 24, 96, 'XL');

-- Food 25: Cà phê sữa đá
INSERT INTO `fastbreakfastdb_demo`.`food_size`
    (`discount_percentage`, `price`, `stock`, `food_id`, `id`, `size`)
VALUES
    (0,     15000, 50, 25, 97, 'S'),
    (5.0,   20000, 40, 25, 98, 'M'),
    (7.5,   25000, 30, 25, 99, 'L'),
    (10.0,  30000, 20, 25, 100,'XL');

-- Insert Carts for Users (each row references a valid fbf_user_id)
INSERT INTO `fastbreakfastdb_demo`.`cart`
    (`fbf_user_id`, `id`)
VALUES
    (1, 1),
    (2, 2),
    (3, 3);

-- Insert Cart Items (each row references an existing cart_id and food_size_id)
--INSERT INTO `fastbreakfastdb_demo`.`cart_item`
--    (`discount_percentage`, `price`, `quantity`, `cart_id`, `food_size_id`, `id`)
--VALUES
--    (0.0, 10000, 1, 1, 1, 1),
--    (0.0, 10000, 1, 2, 1, 2),
--    (0.0, 10000, 3, 3, 1, 3),
--    (5.0, 15000, 2, 3, 2, 4);

-- Insert Discount Codes
INSERT INTO `fastbreakfastdb_demo`.`discount_code`
    (`discount_percentage`, `expiration_date`, `id`, `code`)
VALUES
    (10.0, DATE_ADD(NOW(), INTERVAL 30 DAY), 1, 'DISCOUNT10'),
    (15.0, DATE_ADD(NOW(), INTERVAL 60 DAY), 2, 'SAVE15');

-- Insert Orders (each order must reference an existing fbf_user and discount_code)
--INSERT INTO `fastbreakfastdb_demo`.`fbf_order`
--    (`discounted_total_price`, `created_at`, `discount_code_id`, `fbf_user_id`, `id`, `address`, `phone_number`)
--VALUES
--    (25.98, NOW(), 1, 1, 1, '123 Main St', '1234567890'),
--    (25.98, NOW(), 1, 2, 2, '456 Park Ave', '0987654321'),
--    (25.98, NOW(), 1, 3, 3, 'Sơn Hòa, Phú Yên', '0376816194');

-- Insert Order Items (each order_item references an existing order and food_size)
--INSERT INTO `fastbreakfastdb_demo`.`order_item`
--    (`discount_percentage`, `discounted_price`, `quantity`, `fbf_order_id`, `food_size_id`, `id`)
--VALUES
--    (0.0, 10000, 2, 1, 1, 1),
--    (0.0, 10000, 2, 2, 1, 2),
--    (5.0, 14250, 2, 3, 2, 3);

