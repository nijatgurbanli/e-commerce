package az.idrak.appv1.repository;

import java.util.Optional;

import az.idrak.appv1.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.idrak.appv1.entity.cart.Cart;
import az.idrak.appv1.entity.cart.CartItem;
import az.idrak.appv1.entity.product.Product;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long>{
    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);

    Optional<CartItem> findByCartUser(User user);
}
