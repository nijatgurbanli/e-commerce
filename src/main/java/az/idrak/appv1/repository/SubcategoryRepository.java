package az.idrak.appv1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.idrak.appv1.entity.product.Subcategory;
import az.idrak.appv1.entity.product.Type;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
}
