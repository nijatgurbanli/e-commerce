package az.idrak.appv1.entity.product;

import az.idrak.appv1.entity.cart.CartItem;
import az.idrak.appv1.entity.order.OrderItem;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "product_seq", sequenceName = "product_sequence", allocationSize = 1, initialValue = 1)
    private Long id;

    private String name;
    private String description;
    private String brand;
    @CreationTimestamp
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime updatedDate;
    private Long price;
    private Long discount;
    private Long stock;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
    private List<String> images;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", foreignKey = @ForeignKey(value = ConstraintMode.PROVIDER_DEFAULT))
    private Type type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(value = ConstraintMode.PROVIDER_DEFAULT))
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subcategory_id", foreignKey = @ForeignKey(value = ConstraintMode.PROVIDER_DEFAULT))
    private Subcategory subcategory;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> chartItems;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;
}