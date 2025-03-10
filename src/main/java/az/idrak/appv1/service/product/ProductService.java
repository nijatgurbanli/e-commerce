package az.idrak.appv1.service.product;

import az.idrak.appv1.dto.TypeDTO;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import az.idrak.appv1.dto.ProductDTO;
import az.idrak.appv1.entity.product.Product;
import az.idrak.appv1.exception.BusinessException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    ProductDTO save(ProductDTO productDTO, MultipartFile[] files) throws BusinessException, IOException;

    Optional<ProductDTO> findById(Long id);

    List<ProductDTO> findAllProducts();

    Page<ProductDTO> findAll(int page, int parPage, String searchValue);

    ProductDTO update(ProductDTO productDTO, MultipartFile[] files, Long id) throws IOException;

    ProductDTO delete(Long id) throws IOException;

    List<ProductDTO> getProductListBySubcategoryId(Long id) ;
    List<ProductDTO> getProductListByCategoryId(Long id) ;
    List<ProductDTO> getProductListByTypeId(Long id) ;

}
