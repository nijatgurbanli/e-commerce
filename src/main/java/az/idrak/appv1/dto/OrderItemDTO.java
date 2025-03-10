package az.idrak.appv1.dto;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class OrderItemDTO {

    private Long id;
    private EnumDTO order;
    private EnumDTO product;
    private Long quantity;
    private double price;
}
