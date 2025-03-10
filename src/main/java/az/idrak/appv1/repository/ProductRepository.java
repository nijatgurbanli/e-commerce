package az.idrak.appv1.repository;

import az.idrak.appv1.entity.product.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.idrak.appv1.entity.product.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllBySubcategoryId(Long id);
    List<Product> findAllByCategoryId(Long id);
    List<Product> findAllByTypeId(Long id);
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
