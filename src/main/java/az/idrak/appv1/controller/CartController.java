package az.idrak.appv1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import az.idrak.appv1.dto.CartItemDTO;
import az.idrak.appv1.service.cart.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("/add")
    public ResponseEntity<CartItemDTO> addProductToCart(
        @RequestParam Long userId,
        @RequestParam Long productId,
        @RequestParam Long quantity) {
        CartItemDTO cartItemDto = cartService.addProductToCart(userId, productId, quantity);
        return ResponseEntity.ok(cartItemDto);
    }
}
