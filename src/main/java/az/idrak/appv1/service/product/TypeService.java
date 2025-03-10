package az.idrak.appv1.service.product;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import az.idrak.appv1.dto.ProductDTO;
import az.idrak.appv1.dto.TypeDTO;
import az.idrak.appv1.exception.BusinessException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface TypeService {

    TypeDTO save(TypeDTO typeDTO);

    Optional<TypeDTO> findById(Long id);

    List<TypeDTO> findAllType();

    Page<TypeDTO> findAll(int page, int parPage, String searchValue);

    TypeDTO update(TypeDTO typeDTO) throws IOException;

    TypeDTO delete(Long id) throws IOException;
}
