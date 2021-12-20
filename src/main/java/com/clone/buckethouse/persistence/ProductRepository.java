package com.clone.buckethouse.persistence;

import com.clone.buckethouse.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {
}
