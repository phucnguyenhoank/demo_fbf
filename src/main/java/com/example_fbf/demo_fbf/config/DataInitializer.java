package com.example_fbf.demo_fbf.config;

import com.example_fbf.demo_fbf.entity.*;
import com.example_fbf.demo_fbf.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private FbfUserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private FoodSizeRepository foodSizeRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private FbfOrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private DiscountCodeRepository discountCodeRepository;

    @Override
    public void run(String... args) throws Exception {
        // Create multiple users
        List<FbfUser> users = Arrays.asList(
                new FbfUser(null, "john_doe", "password123", "john@example.com", "John Doe", "1234567890", "123 Main St", FbfRole.FBF_USER, null, null),
                new FbfUser(null, "jane_smith", "pass456", "jane@example.com", "Jane Smith", "0987654321", "456 Park Ave", FbfRole.FBF_USER, null, null)
        );
        users = userRepository.saveAll(users);

        // Create multiple categories
        List<Category> categories = Arrays.asList(
                new Category(null, "Pizza"),
                new Category(null, "Burger"),
                new Category(null, "Pasta")
        );
        categories = categoryRepository.saveAll(categories);

        // Create multiple food items
        Food food1 = new Food();
        food1.setName("Margherita");
        food1.setDescription("Classic cheese pizza");
        food1.setImageUrl("https://example.com/margherita.png");
        food1.setCategory(categories.get(0)); // Pizza

        Food food2 = new Food();
        food2.setName("Pepperoni");
        food2.setDescription("Spicy pepperoni pizza");
        food2.setImageUrl("https://example.com/pepperoni.png");
        food2.setCategory(categories.get(0)); // Pizza

        Food food3 = new Food();
        food3.setName("Cheeseburger");
        food3.setDescription("Beef burger with cheese");
        food3.setImageUrl("https://example.com/cheeseburger.png");
        food3.setCategory(categories.get(1)); // Burger

        Food food4 = new Food();
        food4.setName("Spaghetti Carbonara");
        food4.setDescription("Traditional Italian pasta");
        food4.setImageUrl("https://example.com/carbonara.png");
        food4.setCategory(categories.get(2)); // Pasta

        List<Food> foods = Arrays.asList(food1, food2, food3, food4);
        foods = foodRepository.saveAll(foods);

        // Create multiple food sizes for each food (sample sizes S, M, L)
        FoodSize fs1 = new FoodSize();
        fs1.setSize("M");
        fs1.setPrice(9.99);
        fs1.setDiscountPercentage(10.0);
        fs1.setFood(food1);

        FoodSize fs2 = new FoodSize();
        fs2.setSize("L");
        fs2.setPrice(12.99);
        fs2.setDiscountPercentage(15.0);
        fs2.setFood(food1);

        FoodSize fs3 = new FoodSize();
        fs3.setSize("M");
        fs3.setPrice(10.99);
        fs3.setDiscountPercentage(null);
        fs3.setFood(food2);

        FoodSize fs4 = new FoodSize();
        fs4.setSize("L");
        fs4.setPrice(13.99);
        fs4.setDiscountPercentage(5.0);
        fs4.setFood(food2);

        FoodSize fs5 = new FoodSize();
        fs5.setSize("M");
        fs5.setPrice(7.99);
        fs5.setDiscountPercentage(null);
        fs5.setFood(food3);

        FoodSize fs6 = new FoodSize();
        fs6.setSize("M");
        fs6.setPrice(11.99);
        fs6.setDiscountPercentage(20.0);
        fs6.setFood(food4);

        List<FoodSize> sizes = Arrays.asList(fs1, fs2, fs3, fs4, fs5, fs6);
        sizes = foodSizeRepository.saveAll(sizes);

        // Create carts for each user
        for (FbfUser user : users) {
            Cart cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);

            // Add a cart item for each cart using a random food size from our list
            CartItem cartItem = new CartItem();
            // For simplicity, using the first size; you could randomize or iterate as needed.
            cartItem.setFoodSize(sizes.get(0));
            cartItem.setPrice(sizes.get(0).getPrice());
            cartItem.setDiscountPercentage(sizes.get(0).getDiscountPercentage());
            cartItem.setQuantity(1);
            cartItem.setCart(cart);
            cartItemRepository.save(cartItem);
        }

        // Create multiple discount codes
        List<DiscountCode> discountCodes = Arrays.asList(
                new DiscountCode(null, "DISCOUNT10", 10.0, LocalDateTime.now().plusDays(30)),
                new DiscountCode(null, "SAVE15", 15.0, LocalDateTime.now().plusDays(60))
        );
        discountCodes = discountCodeRepository.saveAll(discountCodes);

        // Create orders for each user with order items and applying discount codes randomly
        for (FbfUser user : users) {
            FbfOrder order = new FbfOrder();
            order.setTotalPrice(0.0); // will update later based on order items
            order.setPhoneNumber(user.getPhoneNumber());
            order.setAddress(user.getAddress());
            order.setCreatedAt(LocalDateTime.now());
            order.setUser(user);
            // For demo, assign the first discount code to one order (or you can randomize)
            order.setDiscountCode(discountCodes.get(0));
            order = orderRepository.save(order);

            // Create an order item for the order using one of the food sizes
            OrderItem orderItem = new OrderItem();
            // Using the second size for variety, adjust as needed
            orderItem.setFoodSize(sizes.get(1));
            orderItem.setPrice(sizes.get(1).getPrice());
            orderItem.setDiscountPercentage(sizes.get(1).getDiscountPercentage());
            orderItem.setQuantity(2);
            orderItem.setOrder(order);
            orderItemRepository.save(orderItem);

            // Update the total price based on the order item(s)
            order.setTotalPrice(orderItem.getPrice() * orderItem.getQuantity());
            orderRepository.save(order);
        }

        System.out.println("Seed data with multiple records inserted.");
    }
}
