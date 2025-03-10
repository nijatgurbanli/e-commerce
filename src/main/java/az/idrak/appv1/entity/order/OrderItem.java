package az.idrak.appv1.entity.order;

import az.idrak.appv1.entity.product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "order_item_seq", sequenceName = "order_item_sequence", allocationSize = 1, initialValue = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", foreignKey = @ForeignKey(value = ConstraintMode.PROVIDER_DEFAULT, name = "order_item_order_fk"))
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(value = ConstraintMode.PROVIDER_DEFAULT, name = "order_item_product_fk"))
    private Product product;

    private Long quantity;

    private double price;
}
