package az.idrak.appv1.entity.cart;

import az.idrak.appv1.entity.product.Product;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "cart_item_seq_generator", sequenceName = "cart_item_sequence", allocationSize = 1, initialValue = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", foreignKey = @ForeignKey(value = ConstraintMode.PROVIDER_DEFAULT, name = "cart_item_cart_fk"))
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(value = ConstraintMode.PROVIDER_DEFAULT, name = "cart_item_product_fk"))
    private Product product;

    private Long quantity;

    private double price;
}
