package az.idrak.appv1.service.product;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import az.idrak.appv1.dto.ProductDTO;
import az.idrak.appv1.dto.TypeDTO;
import az.idrak.appv1.entity.product.Product;
import az.idrak.appv1.entity.product.Type;
import az.idrak.appv1.exception.BusinessException;
import az.idrak.appv1.repository.ProductRepository;
import az.idrak.appv1.repository.TypeRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Override
    public TypeDTO save(TypeDTO typeDTO) {
        ModelMapper modelMapper = new ModelMapper();

        Type type = typeRepository.save(modelMapper.map(typeDTO, Type.class));
        return modelMapper.map(type, TypeDTO.class);
    }

    @Override
    public Optional<TypeDTO> findById(Long id) {
        ModelMapper modelMapper = new ModelMapper();
        Optional<Type> type = typeRepository.findById(id);
        return Optional.of(modelMapper.map(type.get(), TypeDTO.class));
    }

    @Override
    public List<TypeDTO> findAllType() {
        return null;
    }

    @Override
    public Page<TypeDTO> findAll(int page, int parPage, String searchValue) {
        ModelMapper modelMapper = new ModelMapper();
        Pageable pageable = PageRequest.of(page, parPage);

        if (searchValue != null && !searchValue.isEmpty()) {
            return typeRepository.findByNameContainingIgnoreCase(searchValue, pageable)
                    .map(type -> modelMapper.map(type, TypeDTO.class));
        }

        return typeRepository.findAll(pageable)
                .map(type -> modelMapper.map(type, TypeDTO.class));
    }

    @Override
    public TypeDTO update(TypeDTO typeDTO) throws IOException {
        return null;
    }

    @Override
    public TypeDTO delete(Long id) throws IOException {
        return null;
    }
}
