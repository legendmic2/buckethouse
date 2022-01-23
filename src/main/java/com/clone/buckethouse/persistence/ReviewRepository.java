package com.clone.buckethouse.persistence;

import com.clone.buckethouse.model.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, String> {
    List<ReviewEntity> findByProductId(String productId);
    Optional<ReviewEntity> findById(String id);
    List<ReviewEntity> findByUserId(String UserId);
    List<ReviewEntity> findAllBy();


}