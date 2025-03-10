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
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "type_seq", sequenceName = "type_sequence", allocationSize = 1, initialValue = 1)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "type", fetch = FetchType.LAZY)
    private Set<Category> categories;

    @OneToMany(mappedBy = "type", fetch = FetchType.LAZY)
    private Set<Subcategory> subCategories;
}
