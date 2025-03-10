package az.idrak.appv1.service.order;

import az.idrak.appv1.entity.cart.CartItem;
import az.idrak.appv1.entity.order.Order;
import az.idrak.appv1.entity.order.OrderItem;
import az.idrak.appv1.entity.product.Product;
import az.idrak.appv1.entity.user.User;
import az.idrak.appv1.repository.*;
import az.idrak.appv1.service.payment.PaymentService;
import com.stripe.model.PaymentIntent;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class OrderService {
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    private final PaymentService paymentService;

    public OrderService(OrderItemRepository orderItemRepository,
                        OrderRepository orderRepository,
                        UserRepository userRepository,
                        CartItemRepository cartItemRepository,
                        ProductRepository productRepository, PaymentService paymentService) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.paymentService = paymentService;
    }

    @Transactional
    public void addOrderItem(Long userId, List<Long> cartItemId, Integer statusId, String address) throws Exception {

        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            List<CartItem> cartItemList = cartItemRepository.findAllById(cartItemId);

            // 1. Order yaradılır
            Order order = new Order();
            order.setUser(user);
            double totalOrderPrice = cartItemList.stream()
                    .mapToDouble(CartItem::getPrice)
                    .sum();
            order.setTotalPrice(totalOrderPrice);
            order.setCreatedAt(LocalDateTime.now());
            order.setStatus("PENDING");
            order.setAddress(address);
            orderRepository.save(order);

            // 2. Ödəniş başlanır
            PaymentIntent paymentIntent = paymentService.createPaymentIntent(
                    (long) order.getTotalPrice(),
                    "usd"
            );

//            "examplePaymentIdFromFrontend"
            // 3. Order statusu yenilənir
//            if ("succeeded".equals(paymentIntent.getStatus())) {
            order.setStatus("CONFIRMED");
//            } else {
//                throw new RuntimeException("Payment failed");
//            }
            orderRepository.save(order);

            for (CartItem cartItem : cartItemList) {
                Product product = productRepository.findById(cartItem.getProduct().getId())
                        .orElseThrow(() -> new IllegalArgumentException("Product not found"));

                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(order);
                orderItem.setProduct(cartItem.getProduct());
                if (product.getStock() < cartItem.getQuantity()) {
                    throw new IllegalArgumentException("There are not enough products. There are " + product.getStock() + " in stock");
                } else {
                    orderItem.setQuantity(cartItem.getQuantity());
                }
                orderItem.setPrice(cartItem.getPrice());
                orderItemRepository.save(orderItem);

                product.setStock(product.getStock() - cartItem.getQuantity());
                productRepository.save(product);
            }
            cartItemRepository.deleteAllById(cartItemId);
        } catch (Exception e) {
            throw new RuntimeException("Order or payment failed: " + e.getMessage());
        }
    }
}