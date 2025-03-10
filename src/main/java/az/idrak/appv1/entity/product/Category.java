package az.idrak.appv1.entity.product;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "category_seq", sequenceName = "category_sequence", allocationSize = 1, initialValue = 1)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", foreignKey = @ForeignKey(value = ConstraintMode.PROVIDER_DEFAULT))
    private Type type;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private Set<Subcategory> subCategories;
}
