package az.idrak.appv1;

import org.junit.jupiter.api.Test;

import az.idrak.appv1.service.product.ProductServiceImpl;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ProductServiceTest {

    ProductServiceImpl productService = new ProductServiceImpl();

    @Test
    void whenUserInputNotCorrectThenException(){
        Long id  = 503L;

        assertThatThrownBy(() -> productService.findById(id))
                .isInstanceOf(RuntimeException.class);

    }
}
