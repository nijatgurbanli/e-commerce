package az.idrak.appv1.entity.product;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Subcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "subCategory_seq", sequenceName = "subCategory_sequence", allocationSize = 1, initialValue = 1)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", foreignKey = @ForeignKey(value = ConstraintMode.PROVIDER_DEFAULT))
    private Type type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(value = ConstraintMode.PROVIDER_DEFAULT))
    private Category category;
}
