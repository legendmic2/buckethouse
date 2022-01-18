package com.clone.buckethouse.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Review")
public class ReviewEntity {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy="uuid")
    private String id;          //Review의 id

/*    @ManyToOne
    @JoinColumn(name="USER_ID")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name="PRODUCT_ID")
    private ProductEntity productEntity;
*/
    private String productId;   //product의 id product table의 id 참조     /   ** FK
    private String userId;      //Reivew를 등록한 유저, user table의 id 참조 /   ** FK


    private String content;     //Review 의 내용
    private String title;       //Review의 제목
    private int grade;          //리뷰의 점수
}