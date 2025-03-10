package az.idrak.appv1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.idrak.appv1.dto.CategoryDTO;
import az.idrak.appv1.dto.TypeDTO;
import az.idrak.appv1.service.product.CategoryService;
import az.idrak.appv1.service.product.TypeService;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    public CategoryService categoryService;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("/save")
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(categoryService.save(categoryDTO));
    }
//
//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
//    @GetMapping("/images/{filename}")
//    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
//        try {
//            Path filePath = Paths.get(uploadDir).resolve(filename);
//            Resource resource = new UrlResource(filePath.toUri());
//
//            if (resource.exists()) {
//                return ResponseEntity.ok()
//                        .contentType(MediaType.IMAGE_PNG)
//                        .body(resource);
//            } else {
//                return ResponseEntity.notFound().build();
//            }
//        } catch (MalformedURLException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
//    @GetMapping("/{id}")
//    public ResponseEntity<Optional<ProductDTO>> getById(@PathVariable Long id) {
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(productService.findById(id));
//    }
//
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