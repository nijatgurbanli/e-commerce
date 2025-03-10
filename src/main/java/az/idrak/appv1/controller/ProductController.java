package az.idrak.appv1.controller;

import az.idrak.appv1.dto.TypeDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import az.idrak.appv1.dto.ProductDTO;
import az.idrak.appv1.entity.product.Product;
import az.idrak.appv1.exception.BusinessException;
import az.idrak.appv1.repository.ProductRepository;
import az.idrak.appv1.service.product.ProductService;
import az.idrak.appv1.unit.RESTResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Value("${file.upload-dir}")
    private String uploadDir;

    @Autowired
    public ProductService productService;

    @Autowired
    public ProductRepository productRepository;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("/save")
    public ResponseEntity<ProductDTO> create(@RequestPart("text") String productJson,
                                             @RequestPart("file") MultipartFile[] files) throws BusinessException, IOException {
        //Requestde birbasa dto qebul ede bilmir ona gore sonradan dto ya kocurdum
        ProductDTO productDTO = new ObjectMapper().readValue(productJson, ProductDTO.class);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.save(productDTO, files));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/images/{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        try {
            Path filePath = Paths.get(uploadDir).resolve(filename);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_PNG)
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProductDTO>> getById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.findById(id));
    }

//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping("/all")
//    public ResponseEntity<List<ProductDTO>> getAll() {
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(productService.findAll());
//    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/all")
    public ResponseEntity<Page<ProductDTO>> getAllProducts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int parPage,
            @RequestParam(required = false) String searchValue) {

        Page<ProductDTO> products = productService.findAll(page-1, parPage, searchValue);
        return ResponseEntity.ok(products);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/subcategory/{id}")
    public ResponseEntity<List<ProductDTO>> getAllBySubcategoryId(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.getProductListBySubcategoryId(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/category/{id}")
    public ResponseEntity<List<ProductDTO>> getAllByCategoryId(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.getProductListByCategoryId(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/type/{id}")
    public ResponseEntity<List<ProductDTO>> getAllByTypeId(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.getProductListByTypeId(id));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDTO> update(@RequestPart("text") String productJson,
                                             @RequestPart("file") MultipartFile[] files,
                                             @PathVariable Long id) throws IOException {
        ProductDTO productDTO = new ObjectMapper().readValue(productJson, ProductDTO.class);
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.update(productDTO, files, id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ProductDTO> delete(@PathVariable Long id) {
        RESTResponse<ProductDTO> response = new RESTResponse<>();
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(productService.delete(id));
        } catch (IOException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}