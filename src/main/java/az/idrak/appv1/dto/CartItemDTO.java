package az.idrak.appv1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CartItemDTO {

    private Long id;

    private EnumDTO cart;

    private EnumDTO product;

    private Long quantity;

    private double price;
}
