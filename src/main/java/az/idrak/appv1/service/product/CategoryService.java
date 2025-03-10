package az.idrak.appv1.service.product;

import az.idrak.appv1.dto.CategoryDTO;
import az.idrak.appv1.dto.TypeDTO;

public interface CategoryService {

    CategoryDTO save(CategoryDTO categoryDTO);

//    Optional<ProductDTO> findById(Long id);
//
//    List<ProductDTO> findAll();
//
//    ProductDTO update(ProductDTO productDTO, MultipartFile[] files, Long id) throws IOException;
//
//    ProductDTO delete(Long id) throws IOException;
}
