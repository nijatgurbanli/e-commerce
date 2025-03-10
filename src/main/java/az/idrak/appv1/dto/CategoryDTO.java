package az.idrak.appv1.dto;

import az.idrak.appv1.entity.product.Type;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryDTO {
    private Long id;

    private String name;

    private EnumDTO type;
}
