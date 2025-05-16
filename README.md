# 🍱 FBF Mobile - Hệ thống đặt món ăn sáng

Ứng dụng di động và hệ thống backend hỗ trợ người dùng đặt món ăn sáng, quản lý giỏ hàng, đơn hàng, ưu đãi, và tài khoản cá nhân.

---

## 📦 Source Code

- 🔙 Backend: [demo_fbf (phuc_dev)](https://github.com/phucnguyenhoank/demo_fbf/tree/phuc_dev)
- 📱 Frontend (Mobile): [demo_fbfmobile (phuc_dev)](https://github.com/phucnguyenhoank/demo_fbfmobile/tree/phuc_dev)

---

## 🧩 Mô hình cơ sở dữ liệu

### Quan hệ và Ràng buộc

- **Một `Food`** thuộc duy nhất **một `Category`** và **một `FoodSize`**.
- `Food` tích hợp thuộc tính `size` luôn cho đơn giản.
- `FbfUser` có **một `Cart` duy nhất**, và một `Cart` thuộc về duy nhất một `FbfUser`.
- Một `Cart` chứa nhiều `CartItem`. Mỗi `CartItem` phải thuộc về một `Cart`.
- Người dùng có thể thêm nhiều `CartItem` vào `Cart` của họ.
- Một `FbfOrder` chứa nhiều `OrderItem`.
- Một người dùng có thể tạo và thanh toán nhiều `FbfOrder`.
- Một `DiscountCode`:
  - **Không áp dụng** trực tiếp lên `CartItem`, nhưng **hiển thị giá đã giảm** trong giỏ hàng.
  - **Được áp dụng** lên `OrderItem` sau khi tạo từ `CartItem`.
  - **Có thể áp dụng** trực tiếp lên `FbfOrder`, khi được hệ thống tặng cho người dùng dựa trên lịch sử mua hàng.

---
### Lược đồ ERD
* ![image](https://github.com/user-attachments/assets/6038be39-11a9-4b59-9c2d-c6ce9c22a6a8)

## 🗂️ Thiết kế bảng dữ liệu (có khóa chính - khóa ngoại)

| Bảng | Các trường |
|------|------------|
| `FbfUser` | `id`, `username`, `password`, `email`, `name`, `phone_number`, `address` |
| `Food` | `id`, `name`, `description`, `image_url`, `category_id` |
| `FoodSize` | `id`, `size` (S/M/L/XL), `price`, `food_id` |
| `Category` | `id`, `name` |
| `Cart` | `id`, `fbf_user_id` |
| `CartItem` | `id`, `price`, `quantity`, `food_id`, `cart_id` |
| `FbfOrder` | `id`, `total_price`, `phone_number`, `address`, `created_at`, `fbf_user_id` |
| `OrderItem` | `id`, `price`, `quantity`, `fbf_order_id`, `cart_item_id` |
| `DiscountCode` | `id`, `code`, `discount_percentage`, `expiration_date`, `expired`, `discounted_id (nullable)` |
<!-- | `Payment` | `id`, `amount`, `fbf_user_id`, `fbf_order_id` | -->

---

## 🔒 API Authentication

- Sử dụng JWT Token cho các API bảo mật.
- Header: `Authorization: Bearer <token>`

---

## 📡 Các API chính (Backend)

### Auth

- `POST /api/v1/auth/send-otp` - Gửi OTP qua email
- `POST /api/v1/auth/register` - Đăng ký tài khoản
- `POST /api/v1/auth/authenticate` - Đăng nhập
- `POST /api/v1/auth/reset-password-request` - Yêu cầu đổi mật khẩu
- `POST /api/v1/auth/reset-password` - Đặt lại mật khẩu

### Người dùng

- `GET /api/v1/users/me` - Lấy thông tin người dùng
- `PUT /api/v1/users/me` - Cập nhật thông tin người dùng

### Giỏ hàng

- `GET /api/v1/cart-items/display` - Lấy danh sách món trong giỏ
- `POST /api/v1/cart-items/add` - Thêm món vào giỏ
- `PUT /api/v1/cart-items/update` - Cập nhật món trong giỏ
- `DELETE /api/v1/cart-items/{id}` - Xoá món khỏi giỏ

### Món ăn

- `GET /api/v1/food/all` - Lấy tất cả món ăn
- `GET /api/v1/food/{id}` - Lấy chi tiết món ăn
- `GET /api/v1/food/search/category-id` - Lọc theo category
- `GET /api/v1/food/search/by-price` - Lọc theo khoảng giá
- `GET /api/v1/food/search/full` - Lọc nâng cao (tên, giá, danh mục)

### Đơn hàng

- `POST /api/v1/fbf-orders/create-undo` - Tạo đơn hàng (chờ xác nhận)
- `POST /api/v1/fbf-orders/{id}/confirm` - Xác nhận đơn hàng
- `POST /api/v1/fbf-orders/{id}/undo` - Huỷ đơn hàng
- `DELETE /api/v1/fbf-orders/{id}/delete-canceled` - Xoá đơn đã huỷ
- `GET /api/v1/fbf-orders/get-mine` - Lịch sử đặt hàng
- `GET /api/v1/fbf-orders/get` - Lấy đơn theo ID
- `GET /api/v1/order-item/get` - Lấy item trong đơn hàng

---

## ✅ Công nghệ sử dụng

- 📱 **Frontend (Mobile)**: Java + Retrofit (Android)
- 🔙 **Backend**: Spring Boot + JWT Auth + JPA
- 🗄️ **Database**: MySQL
- ☁️ **Triển khai**: Local 

---

## 📌 Ghi chú

- Token JWT cần được đính kèm trong mọi request bảo mật (`Authorization: Bearer <token>`).
- API đã phân biệt rõ các bước tạo, xác nhận và huỷ đơn hàng.
- DiscountCode không áp dụng trực tiếp vào `CartItem` để đơn giản hoá kiểm soát và logic tính toán.

---

## 🧪 Chạy thử cục bộ (local)

```bash
# Backend
cd demo_fbf
./mvnw spring-boot:run

# Frontend
Mở Android Studio -> Run ứng dụng

# Cấu hình baseUrl trong Retrofit phải trỏ đúng server backend đang chạy
