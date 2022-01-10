package com.clone.buckethouse.controller;


import com.clone.buckethouse.dto.ResponseDTO;
import com.clone.buckethouse.dto.ReviewDTO;
import com.clone.buckethouse.model.ReviewEntity;
import com.clone.buckethouse.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/create")
    public ResponseEntity<?> createReview_prac(){

        ReviewEntity reviewEntity = reviewService.insertReview();
        List<ReviewEntity> list = new ArrayList<>();
        list.add(reviewEntity);
        ResponseDTO<ReviewEntity> response = ResponseDTO.<ReviewEntity>builder().data(list).build();
        return ResponseEntity.ok().body(response);



    }

    @PostMapping
    public ResponseEntity<?> createReivew(@RequestBody ReviewDTO dto){
        try{
            String temp = "woojinkim";

            // 1. toReviewEntity 로 변환
            ReviewEntity entity = ReviewDTO.toReviewEntity(dto);

            // 2. Review id를 null로 초기화화
            entity.setId(null);

            // 3. 임시 Review id 설정
            entity.setId(temp);

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
    public ResponseEntity<?> retrieveReviewList(){
        String temp = "smapleID";           //create시 id로 했는데  리스트 찾을 땐 productId로 함..

        //1. 서비스의 메서드의 retrieve()를 사용해 ReviewList를 가져온다
        List<ReviewEntity> entities = reviewService.retrieve(temp);

        //2. 자바 스트림을 이용해 리턴된 엔티티 리스트를 ReviewDTO리스트로 변환한다.
        List<ReviewDTO> dtos = entities.stream().map(ReviewDTO::new).collect(Collectors.toList());

        //3. 변환된 ReviewDTO리스트를 이용해 ResponseDTO를 초기화한다.
        ResponseDTO<ReviewDTO> response = ResponseDTO.<ReviewDTO>builder().data(dtos).build();

        //4.리턴한다.
        return ResponseEntity.ok().body(response);
    }

    @PutMapping
    public ResponseEntity<?> updateReview(@RequestBody ReviewDTO dto){
        String temp = "2c9e818c7e3eed68017e3eee8ae50000";

        //1. dto를 entity로 변환한다.
        ReviewEntity entity = ReviewDTO.toReviewEntity(dto);

        //2. id를 temp로 초기화한다.
        entity.setId(temp);

        //3. 서비스를 이용해 entity를 업데이트한다.
        List<ReviewEntity> entities=reviewService.update(entity);

        //4. 자바 스트림을 이용해 리턴된 엔티티 리스트를 ReviewDTO로 변환한다.
        List<ReviewDTO> dtos = entities.stream().map(ReviewDTO::new).collect(Collectors.toList());

        //5. 변환된 ReviewDTO리스트를 이용해 ResponseDTO를 초기화한다.
        ResponseDTO<ReviewDTO> response = ResponseDTO.<ReviewDTO>builder().data(dtos).build();

        return ResponseEntity.ok().body(response);

    }

    @DeleteMapping
    public ResponseEntity<?> deleteReview(@RequestBody ReviewDTO dto){
        try {
            String temp = "2c9e818c7e3f7e33017e3f7e4e8a0000";

            //1. ReviewEntity로 변환한다.
            ReviewEntity entity = ReviewDTO.toReviewEntity(dto);

            entity.setId(temp);

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