package com.clone.buckethouse.controller;


import com.clone.buckethouse.dto.ResponseDTO;
import com.clone.buckethouse.dto.ReviewDTO;
import com.clone.buckethouse.dto.StoreContentDTO;
import com.clone.buckethouse.model.ProductEntity;
import com.clone.buckethouse.model.ReviewEntity;
import com.clone.buckethouse.persistence.ProductRepository;
import com.clone.buckethouse.persistence.ReviewRepository;
import com.clone.buckethouse.persistence.UserRepository;
import com.clone.buckethouse.service.ReviewService;
import com.clone.buckethouse.service.StoreContentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewRepository reviewRepository;

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
    public ResponseEntity<?> createReview(@RequestBody ReviewDTO dto,@AuthenticationPrincipal String userId){
        try{
            //log.info(userId);
            log.info(dto.getProductId());   log.info(dto.getId());
            // 1. toReviewEntity 로 변환
            ReviewEntity entity = ReviewDTO.toReviewEntity(dto);
            entity.setId(null);
            // 3. Review userId 설정
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

    @GetMapping
    public ResponseEntity<?> retrieveReviewWithAuth(@RequestParam(value = "ProductId", required = false) String ProductId,@AuthenticationPrincipal String userId){
        ResponseDTO<ReviewDTO> response = new ResponseDTO<>();

        //userId 기준 review 조회할 경우
        if(ProductId==null) {

            System.out.println("해당 유저로 조회");
            List<ReviewEntity> entities = reviewService.retrieve(userId);

            //2. 자바 스트림을 이용해 리턴된 엔티티 리스트를 ReviewDTO리스트로 변환한다.
            List<ReviewDTO> dtos = entities.stream().map(ReviewDTO::new).collect(Collectors.toList());

            //3. 변환된 ReviewDTO리스트를 이용해 ResponseDTO를 초기화한다.
            response = ResponseDTO.<ReviewDTO>builder().data(dtos).build();
        }

        //ProductId 로 조회할 경우
        if(ProductId!=null) {
            System.out.println("해당 product Id로 조회");
            String product_id = ProductId;
            //Product Id 기준 조회
            List<ReviewEntity> entities = reviewService.retrieveByProductId(product_id);

            //자바 스트림을 이용해 리턴된 엔티티 리스트를 Revier DTO로 변환함.
            List<ReviewDTO> dtos = entities.stream().map(ReviewDTO::new).collect(Collectors.toList());

            response = ResponseDTO.<ReviewDTO>builder().data(dtos).build();
        }

        return ResponseEntity.ok().body(response);
    }


    @PutMapping
    public ResponseEntity<?> updateReview(@RequestBody ReviewDTO dto, @AuthenticationPrincipal String userId){

        //1. dto를 entity로 변환한다.
        ReviewEntity entity = ReviewDTO.toReviewEntity(dto);

        Optional<ReviewEntity> optional = reviewRepository.findById(dto.getId());
        System.out.println(optional.get());

        if(optional.isPresent()) {
            String id = optional.get().getId();
            //2. id를 userId로 초기화한다.
            entity.setUserId(userId);

            entity.setId(id);
        }

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

            entity.setUserId(userId);
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