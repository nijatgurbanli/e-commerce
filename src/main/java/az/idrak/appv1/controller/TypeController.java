package az.idrak.appv1.controller;

import az.idrak.appv1.entity.product.Type;
import az.idrak.appv1.repository.TypeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import az.idrak.appv1.dto.ProductDTO;
import az.idrak.appv1.dto.TypeDTO;
import az.idrak.appv1.exception.BusinessException;
import az.idrak.appv1.service.product.ProductService;
import az.idrak.appv1.service.product.TypeService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/type")
public class TypeController {

    @Autowired
    public TypeService typeService;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> create(@RequestBody TypeDTO typeDTO) {
        Map<String, Object> response = new HashMap<>();

        try {
            TypeDTO savedType = typeService.save(typeDTO);
            response.put("success", true);
            response.put("message", "Category save successfully");
            response.put("data", savedType);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Category save failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<TypeDTO>> getById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(typeService.findById(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/all")
    public ResponseEntity<Page<TypeDTO>> getAllTypes(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int parPage,
            @RequestParam(required = false) String searchValue) {

        Page<TypeDTO> types = typeService.findAll(page-1, parPage, searchValue);
        return ResponseEntity.ok(types);
    }

//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping("/all")
//    public ResponseEntity<List<ProductDTO>> getAll() {
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(productService.findAll());
//    }
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @PutMapping("/update/{id}")
//    public ResponseEntity<ProductDTO> update(@RequestPart("text") String productJson,
//                                             @RequestPart("file") MultipartFile[] files,
//                                             @PathVariable Long id) throws IOException {
//        ProductDTO productDTO = new ObjectMapper().readValue(productJson, ProductDTO.class);
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(productService.update(productDTO, files, id));
//    }
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<ProductDTO> delete(@PathVariable(name = "id") Long id) {
//        RESTResponse<ProductDTO> response = new RESTResponse<>();
//        try {
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body(productService.delete(id));
//        } catch (IOException e) {
//            return ResponseEntity
//                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .build();
//        }
//    }
}