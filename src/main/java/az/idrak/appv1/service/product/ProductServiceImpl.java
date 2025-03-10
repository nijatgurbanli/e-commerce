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
import az.idrak.appv1.entity.product.Product;
import az.idrak.appv1.exception.BusinessException;
import az.idrak.appv1.repository.ProductRepository;

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
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    @Value("${file.upload-dir}")
    private String uploadDir;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductDTO save(ProductDTO productDTO, MultipartFile[] files) throws BusinessException, IOException {
        ModelMapper modelMapper = new ModelMapper();
        List<String> filePathNames = new ArrayList<>();

        for (MultipartFile file : files) {
            String filePath = saveImage(file);
            filePathNames.add(filePath);
        }
        Product product = modelMapper.map(productDTO, Product.class);
        product.setImages(filePathNames);

        Product savedEntity = productRepository.save(product);
        return modelMapper.map(savedEntity, ProductDTO.class);
    }

    //For save method
    private String saveImage(MultipartFile file) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        // Faylın uzantısını götürmək
        String originalFileName = file.getOriginalFilename();
        String extension = "";
        if (originalFileName != null && originalFileName.contains(".")) {
            extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        }

        // Unikal fayl adı yaratmaq
        String hashedFileName = UUID.randomUUID().toString() + extension;
        Path filePath = uploadPath.resolve(hashedFileName);

        // Faylı qeyd etmək
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return hashedFileName;
    }

    @Override
    public Optional<ProductDTO> findById(Long id) {
        ModelMapper modelMapper = new ModelMapper();
        Optional<Product> product = productRepository.findById(id);
        ProductDTO productDTO = modelMapper.map(product.get(), ProductDTO.class);

        List<String> updatedImages = productDTO.getImages().stream()
                .map(image -> "http://localhost:8080/product/images/" + image)
                .collect(Collectors.toList());
        productDTO.setImages(updatedImages);

        return Optional.of(productDTO);
//        return Optional.of(modelMapper.map(product.get(), ProductDTO.class));
    }

    @Override
    public Page<ProductDTO> findAll(int page, int parPage, String searchValue) {
        ModelMapper modelMapper = new ModelMapper();
        Pageable pageable = PageRequest.of(page, parPage);

        Page<Product> products;

        if (searchValue != null && !searchValue.isEmpty()) {
            products = productRepository.findByNameContainingIgnoreCase(searchValue, pageable);
        } else {
            products = productRepository.findAll(pageable);
        }

        return products.map(product -> {
            ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);

            List<String> updatedImages = productDTO.getImages().stream()
                    .map(image -> "http://localhost:8080/product/images/" + image)
                    .collect(Collectors.toList());

            productDTO.setImages(updatedImages);
            return productDTO;
        });
    }

    @Override
    public List<ProductDTO> findAllProducts() {
        ModelMapper modelMapper = new ModelMapper();
        List<Product> productList = productRepository.findAll();

        return productList.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO update(ProductDTO productDTO, MultipartFile[] files, Long id) throws IOException {
        ModelMapper modelMapper = new ModelMapper();
        Product findProduct = productRepository.findById(id).orElseThrow();
        findProduct.setName(productDTO.getName());
        findProduct.setBrand(productDTO.getBrand());
        findProduct.setPrice(productDTO.getPrice());
        findProduct.setDiscount(productDTO.getDiscount());
        findProduct.setStock(productDTO.getStock());
        findProduct.setUpdatedDate(LocalDateTime.now());

        List<String> filePathNames = new ArrayList<>();
        for (MultipartFile file : files) {
            String filePath = saveImage(file);
            filePathNames.add(filePath);
        }
        findProduct.setImages(filePathNames);

        Product savedProduct = productRepository.save(findProduct);
        return modelMapper.map(savedProduct, ProductDTO.class);
    }

    @Override
    public ProductDTO delete(Long id) throws IOException {
        ModelMapper modelMapper = new ModelMapper();
        Optional<Product> product = productRepository.findById(id);
        productRepository.delete(product.get());
        return modelMapper.map(product.get(), ProductDTO.class);
    }

    @Override
    public List<ProductDTO> getProductListBySubcategoryId(Long id) {
        List<Product> productList = productRepository.findAllBySubcategoryId(id);
        List<ProductDTO> responseProductDTOList = new ArrayList<>();

        for (Product product : productList) {
            ModelMapper modelMapper = new ModelMapper();
            responseProductDTOList.add(modelMapper.map(product, ProductDTO.class));
        }
        return responseProductDTOList;
    }

    @Override
    public List<ProductDTO> getProductListByCategoryId(Long id) {
        List<Product> productList = productRepository.findAllByCategoryId(id);
        List<ProductDTO> responseProductDTOList = new ArrayList<>();

        for (Product product : productList) {
            ModelMapper modelMapper = new ModelMapper();
            responseProductDTOList.add(modelMapper.map(product, ProductDTO.class));
        }
        return responseProductDTOList;
    }

    @Override
    public List<ProductDTO> getProductListByTypeId(Long id) {
        List<Product> productList = productRepository.findAllByTypeId(id);
        List<ProductDTO> responseProductDTOList = new ArrayList<>();

        for (Product product : productList) {
            ModelMapper modelMapper = new ModelMapper();
            responseProductDTOList.add(modelMapper.map(product, ProductDTO.class));
        }
        return responseProductDTOList;
    }
}
