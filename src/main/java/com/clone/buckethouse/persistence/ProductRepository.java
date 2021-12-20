package com.clone.buckethouse.persistence;

import com.clone.buckethouse.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {
    List<ProductEntity> findByVendorId(String vendorId);
}
