package az.idrak.appv1.dto;

import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDTO {

    private Long id;

    private String name;

    private String description;

//    private String category;

    private String brand;

    private Long price;

    private Long discount;

    private Long stock;

    private List<String> images;

    private EnumDTO type;

    private EnumDTO category;

    private EnumDTO subcategory;
//
//    private Set<EnumDTO> settings;
}
