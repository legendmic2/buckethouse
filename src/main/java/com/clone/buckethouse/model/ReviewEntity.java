package com.clone.buckethouse.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private String id;      //오브젝트의 id?
    private String productId;   //product table의 id 참조
    private String userId;      //user table의 id 참조
    private String content;
    private String title;
    private int grade;
}