package az.idrak.appv1.service.cart;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.idrak.appv1.dto.CartItemDTO;
import az.idrak.appv1.entity.cart.Cart;
import az.idrak.appv1.entity.cart.CartItem;
import az.idrak.appv1.entity.product.Product;
import az.idrak.appv1.entity.user.User;
import az.idrak.appv1.repository.CartItemRepository;
import az.idrak.appv1.repository.CartRepository;
import az.idrak.appv1.repository.ProductRepository;
import az.idrak.appv1.repository.UserRepository;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    public CartItemDTO addProductToCart(Long userId, Long productId, Long quantity) {
        // User tap
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Səbəti tap və ya yarat
        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });

        // Məhsulu tap
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Mövcud CartItem yoxla
        Optional<CartItem> existingCartItem = cartItemRepository.findByCartAndProduct(cart, product);

        if (existingCartItem.isPresent()) {
            // Əgər mövcud CartItem varsa, miqdarını artır
            ModelMapper modelMapper = new ModelMapper();
            CartItem cartItem = existingCartItem.get();

            if (product.getStock() < quantity + cartItem.getQuantity()) {
                throw new IllegalArgumentException("There are not enough products. There are " + product.getStock() + " in stock");
            } else {
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
            }
            if (product.getDiscount() == null) {
                cartItem.setPrice(cartItem.getPrice() + quantity * product.getPrice());
            } else {
                cartItem.setPrice(cartItem.getPrice() + quantity * product.getDiscount());
            }
            CartItem entity = cartItemRepository.save(cartItem);
            return modelMapper.map(entity, CartItemDTO.class);
        } else {
            // Yeni CartItem yarat
            ModelMapper modelMapper = new ModelMapper();
            CartItem cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            if (product.getStock() < quantity) {
                throw new IllegalArgumentException("There are not enough products. There are " + product.getStock() + " in stock");
            } else {
                cartItem.setQuantity(quantity);
            }
            cartItem.setQuantity(quantity);
            if (product.getDiscount() == null) {
                cartItem.setPrice(cartItem.getPrice() + quantity * product.getPrice());
            } else {
                cartItem.setPrice(cartItem.getPrice() + quantity * product.getDiscount());
            }
            CartItem entity = cartItemRepository.save(cartItem);
            return modelMapper.map(entity, CartItemDTO.class);
        }
    }
}