package com.clone.buckethouse.service;

import com.clone.buckethouse.model.ProductEntity;
import com.clone.buckethouse.persistence.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class StoreContentService {

    @Autowired
    private ProductRepository repository;

    public List<ProductEntity> create(final ProductEntity entity) {
        validate(entity);

        repository.save(entity);

        log.info("Entity Id : {} is saved.", entity.getId());

        return repository.findByVendorId(entity.getVendorId());
    }

    public List<ProductEntity> retrieve(final String vendorId){
        return repository.findByVendorId(vendorId);
    }

    public List<ProductEntity> update(final ProductEntity entity){
        // (1) 저장할 엔티티가 유효한지 확인한다. 이 메서드는 2.3.1 Create product에서 구현했다.
        validate(entity);

        // (2) 넘겨받은 엔티티 id를 이용해 ProductEntity를 가져온다. 존재하지 않는 엔티티는 업데이트할 수 없기 때문이다.
        final Optional<ProductEntity> original = repository.findById(entity.getId());

        original.ifPresent(product -> {
            // (3) 반환된 ProductEntity가 존재하면 값을 새 entity 값으로 덮어 씌운다.
            product.setTitle(entity.getTitle());
            product.setPrice(entity.getPrice());
            product.setDeliveryFee(entity.getDeliveryFee());
            product.setDescription(entity.getDescription());

            // (4) 데이터베이스에 새 값을 저장한다.
            repository.save(product);
        });

        // 2.3.2 Retrieve product에서 만든 메서드를 이용해 사용자의 모든 product 리스트를 리턴한다.
        return retrieve(entity.getVendorId());
    }
    public List<ProductEntity> delete(final ProductEntity entity) {
        // (1) 저장할 엔티티가 유효한지 확인한다. 이 메서드는 2.3.1 Create product에서 구현했다.
        validate(entity);

        try{
            // (2) 엔티티를 삭제한다.
            repository.delete(entity);
        } catch (Exception e){
            // (3) exception 발생 시 id와 exception을 로깅한다.
            log.error("error deleting entity ", entity.getId(), e);

            // (4) 컨트롤러로 exception을 보낸다. 데이터베이스 내부 로직을 캡슐화하려면 e를 리턴하지 않고 새 exception 오브젝트를 리턴한다.
            throw new RuntimeException("error deleting entity " + entity.getId());
        }
        // (5) 새 product 리스트를 가져와 리턴한다.
        return retrieve(entity.getVendorId());
    }

    private void validate(final ProductEntity entity){
        if (entity == null) {
            log.warn("Entity cannot be null.");
            throw new RuntimeException("Entity cannot be null.");
        }

        if (entity.getVendorId() == null) {
            log.warn("Unknown product.");
            throw new RuntimeException("Unknown product.");
        }
    }

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
