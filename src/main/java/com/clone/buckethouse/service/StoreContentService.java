package com.clone.buckethouse.service;

import com.clone.buckethouse.model.ProductEntity;
import com.clone.buckethouse.persistence.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreContentService {

    @Autowired
    private ProductRepository repository;

    public String testService() {
        // ProductEntity 생성
        ProductEntity entity = ProductEntity.builder().vendorId("testVendor").title("My first product").price(10000).deliveryFee(0).build();
        // ProductEntity 저장
        repository.save(entity);
        // ProductEntity 검색
        ProductEntity savedEntity = repository.findById(entity.getId()).get();
        return savedEntity.getTitle();
    }
}
