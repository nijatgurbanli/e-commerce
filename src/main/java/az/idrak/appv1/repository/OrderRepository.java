package az.idrak.appv1.repository;

import az.idrak.appv1.entity.cart.Cart;
import az.idrak.appv1.entity.order.Order;
import az.idrak.appv1.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByUser(User user);
}
