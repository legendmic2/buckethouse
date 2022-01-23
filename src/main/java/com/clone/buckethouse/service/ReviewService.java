package com.clone.buckethouse.service;


import com.clone.buckethouse.model.ReviewEntity;
import com.clone.buckethouse.persistence.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public String TestReviewService(){
        ReviewEntity reviewEntity = ReviewEntity.builder().id("mw7895la").productId("smapleID").userId("goonoo").title("woojin")
                .content("woojin").grade(5).build();

        reviewRepository.save(reviewEntity);

        ReviewEntity savedEntity = reviewRepository.findById(reviewEntity.getProductId()).get();
        return savedEntity.getId();
    }



    public ReviewEntity insertReview(){
        ReviewEntity reviewEntity = ReviewEntity.builder().id("mw7895la").productId("smapleID").userId("woojin").title("woojin")
                .content("woojin").grade(5).build();
        reviewRepository.save(reviewEntity);
        return reviewEntity;
    }




    //create 메소드
    public List<ReviewEntity> create(final ReviewEntity entity){
        validate(entity);
        reviewRepository.save(entity);

        log.info("review entity id : {} is saved.", entity.getId());

        return reviewRepository.findByProductId(entity.getProductId());
    }

    //retrieve 메소드
    public List<ReviewEntity> retrieve(final String param){
        return reviewRepository.findByUserId(param);
    }
    //retrieve_product 메소드
    public List<ReviewEntity> retrieve_product(final String productId){
        return reviewRepository.findByProductId(productId);
    }
    //retrieve_all 메소드
    public List<ReviewEntity> retrieve_all(){
        return reviewRepository.findAllBy();
    }


    //update 메소드  특정 User가 Signin 하고 특정 ProductId를가진 리스트를 리턴.
    public List<ReviewEntity> update(final ReviewEntity entity){
        //1. 저장할 데이터가 유효한지 확인한다.
        validate(entity);

        //넘겨받은 entity id를 이용해 ReviewEntity를 가져온다. 존재하지 않으면 업데이트할 수 없다.
        final Optional<ReviewEntity> original = reviewRepository.findById(entity.getId());

        original.ifPresent(review ->{
            //3. 반환된 ReviewEntity가 존재하면 값을 새 entity갚으로 덮어 씌운다.
            review.setProductId(entity.getProductId());
            review.setUserId(entity.getUserId());
            review.setContent(entity.getContent());
            review.setTitle(entity.getTitle());
            review.setGrade(entity.getGrade());

            reviewRepository.save(review);
            log.info("service->update->save success");
        });

        return retrieve_product(entity.getProductId());
    }

    public List<ReviewEntity> delete(final ReviewEntity entity){
        //1.삭제할  entity가 유효한지 확인

        validate(entity);

        try {
            //2. 엔티티를 삭제한다.
            reviewRepository.delete(entity);
        }catch(Exception e){
            //3. Exception발생 시 id와 Exception을 로깅한다.
            log.error("Error Deleting", entity.getId(),e);

            //4. 컨트롤러로 exception을 보낸다. 데이터베이스  내부 로직을 캡슐화 하려면 e를 리턴하지 않고
            //새 exception 오브젝트를 리턴한다.
            throw new RuntimeException("error deleting entity" + entity.getId());
        }
        return retrieve(entity.getId());


    }

    public void validate(final ReviewEntity entity){
        if(entity ==null){
            log.warn("ReviewEntity cannot be null");
            throw new RuntimeException("ReivewEntity cannot be null");
        }
        if(entity.getId()==null){
            log.warn("Unknown Reivew");
            throw new RuntimeException("Unknown Review");
        }
    }


}