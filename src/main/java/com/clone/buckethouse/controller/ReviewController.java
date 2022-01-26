package com.clone.buckethouse.controller;


import com.clone.buckethouse.dto.ResponseDTO;
import com.clone.buckethouse.dto.ReviewDTO;
import com.clone.buckethouse.dto.StoreContentDTO;
import com.clone.buckethouse.model.ProductEntity;
import com.clone.buckethouse.model.ReviewEntity;
import com.clone.buckethouse.persistence.ProductRepository;
import com.clone.buckethouse.persistence.UserRepository;
import com.clone.buckethouse.service.ReviewService;
import com.clone.buckethouse.service.StoreContentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoreContentService storeContentService;



    @GetMapping("/create")
    public ResponseEntity<?> createReview_prac(){

        ReviewEntity reviewEntity = reviewService.insertReview();
        List<ReviewEntity> list = new ArrayList<>();
        list.add(reviewEntity);
        ResponseDTO<ReviewEntity> response = ResponseDTO.<ReviewEntity>builder().data(list).build();
        return ResponseEntity.ok().body(response);



    }

    @PostMapping
    public ResponseEntity<?> createReview(@RequestBody ReviewDTO dto, @AuthenticationPrincipal String userId){
        try{
            //ReviewEntity entity = ReviewDTO.toReviewEntity(dto);
            log.info(userId);
            //List<ProductEntity> list = service.retrieve("qwe123");
            //List<StoreContentDTO> dtos = list.stream().map(StoreContentDTO::new).collect(Collectors.toList());
            log.info(dto.getProductId());

            // 1. toReviewEntity 로 변환
            ReviewEntity entity = ReviewDTO.toReviewEntity(dto);

            // 2. Review id를 null로 초기화
            entity.setId(userId);
            entity.setProductId(dto.getProductId());
            // 3. 임시 Review id 설정
            entity.setUserId(userId);

            // 4. 서비스를 이용하여 Review 엔티티를 생성한다.
            List<ReviewEntity> entities = reviewService.create(entity);

            // 5. 자바 스트림을 이용하여 리턴된 엔티티 리스트를 ReviewDTO 리스트로 변환한다.
            List<ReviewDTO> dtos = entities.stream().map(ReviewDTO::new).collect(Collectors.toList());

            // 6. 변환된 ReviewDTO 리스트를 이용해 ResponseDTO를 초기화한다.
            ResponseDTO<ReviewDTO> response = ResponseDTO.<ReviewDTO>builder().data(dtos).build();

            // 7. ResponseDTO를 리턴한다.
            return ResponseEntity.ok().body(response);

        }catch(Exception e){
            String error = e.getMessage();
            ResponseDTO<ReviewDTO> response = ResponseDTO.<ReviewDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }

    }

    @GetMapping("/userid")
    public ResponseEntity<?> retrieveReviewListByUser(@AuthenticationPrincipal String userId){

        //1. 서비스의 메서드의 retrieve()를 사용해 ReviewList를 가져온다
        List<ReviewEntity> entities = reviewService.retrieve(userId);

        //2. 자바 스트림을 이용해 리턴된 엔티티 리스트를 ReviewDTO리스트로 변환한다.
        List<ReviewDTO> dtos = entities.stream().map(ReviewDTO::new).collect(Collectors.toList());

        //3. 변환된 ReviewDTO리스트를 이용해 ResponseDTO를 초기화한다.
        ResponseDTO<ReviewDTO> response = ResponseDTO.<ReviewDTO>builder().data(dtos).build();

        //4.리턴한다.
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/productid")
    public ResponseEntity<?> retrieveReviewListByProduct(){
        //List<ProductEntity> productEntities = service.retrieve("qwe12345");

        List<ReviewEntity> entities = reviewService.retrieve_product("qwe12345");


        //자바 스트림을 이용해 리턴된 엔티티 리스트를 Revier DTO로 변환환
        List<ReviewDTO> dtos = entities.stream().map(ReviewDTO::new).collect(Collectors.toList());

        ResponseDTO<ReviewDTO> response = ResponseDTO.<ReviewDTO>builder().data(dtos).build();

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<?> retrieveReviewListByAll(){
        List<ReviewEntity> entities = reviewService.retrieve_all();

        List<ReviewDTO> dtos = entities.stream().map(ReviewDTO::new).collect(Collectors.toList());

        ResponseDTO<ReviewDTO> response = ResponseDTO.<ReviewDTO>builder().data(dtos).build();

        return ResponseEntity.ok().body(response);
    }

    @PutMapping
    public ResponseEntity<?> updateReview(@RequestBody ReviewDTO dto, @AuthenticationPrincipal String userId){
        //String temp = "2c9e818c7e3eed68017e3eee8ae50000";

        //1. dto를 entity로 변환한다.
        ReviewEntity entity = ReviewDTO.toReviewEntity(dto);

        //2. id를 userId로 초기화한다.
        entity.setUserId(userId);

        entity.setId("2c9e818c7e868a13017e868bdc160001");


        //3. 서비스를 이용해 entity를 업데이트한다.
        List<ReviewEntity> entities=reviewService.update(entity);

        //4. 자바 스트림을 이용해 리턴된 엔티티 리스트를 ReviewDTO로 변환한다.
        List<ReviewDTO> dtos = entities.stream().map(ReviewDTO::new).collect(Collectors.toList());

        //5. 변환된 ReviewDTO리스트를 이용해 ResponseDTO를 초기화한다.
        ResponseDTO<ReviewDTO> response = ResponseDTO.<ReviewDTO>builder().data(dtos).build();

        return ResponseEntity.ok().body(response);

    }

    @DeleteMapping
    public ResponseEntity<?> deleteReview(@RequestBody ReviewDTO dto, @AuthenticationPrincipal String userId){
        try {


            //1. ReviewEntity로 변환한다.
            ReviewEntity entity = ReviewDTO.toReviewEntity(dto);

            entity.setId(userId);

            //2. 서비스를 이용해 entity를 삭제한다.
            List<ReviewEntity> entities = reviewService.delete(entity);

            //3. 자바스트림 이용해 리턴된 엔티티 리스트를 ReviewDTO 리스트로 변환한다.
            List<ReviewDTO> dtos = entities.stream().map(ReviewDTO::new).collect(Collectors.toList());

            //4. 변환된 ReviewDTO 리스트를 이용해 ResponseDTO를 초기화한다.
            ResponseDTO<ReviewDTO> response = ResponseDTO.<ReviewDTO>builder().data(dtos).build();

            //5. ResponseDTO를 리턴한다.
            return ResponseEntity.ok().body(response);
        }catch(Exception e){
            String error = e.getMessage();
            ResponseDTO<ReviewDTO> response = ResponseDTO.<ReviewDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }
}