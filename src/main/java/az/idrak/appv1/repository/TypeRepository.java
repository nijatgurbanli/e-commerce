package az.idrak.appv1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.idrak.appv1.entity.product.Type;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
    Page<Type> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
