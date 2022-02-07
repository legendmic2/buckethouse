package com.clone.buckethouse.dto;


import com.clone.buckethouse.model.ReviewEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDTO {
    private String id;
    private String productId;   //product table의 id 참조
    private String userId;      //user table의 id 참조
    private String content;
    private String title;
    private int grade;


    //데이터를 DTO로 변환해서 리턴하기 위함.
    public ReviewDTO(final ReviewEntity entity){
        this.id = entity.getId();
        this.productId = entity.getProductId();
        this.userId = entity.getUserId();
        this.content= entity.getContent();
        this.title=entity.getTitle();
        this.grade = entity.getGrade();
    }

    public static ReviewEntity toReviewEntity(final ReviewDTO dto){
        return ReviewEntity.builder().id(dto.getId()).productId(dto.getProductId()).userId(dto.getUserId())
                .title(dto.getTitle()).content(dto.getContent()).grade(dto.getGrade()).build();
    }

}