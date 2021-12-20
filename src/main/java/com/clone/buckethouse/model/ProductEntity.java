package com.clone.buckethouse.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductEntity {
    private String id; // Product의 ID
    private String vendorId; // 해당 Product를 생성한 업체의 ID
//    private String vendorName; // 해당 Product를 생성한 업체의 이름
    private String title; // Product의 제목
//    private float star; // 별점 (0.0~5.0)
//    private int reviewCnt; // 리뷰 개수 (추후 review table의 fk)
//    private int discount; // 할인 비율 (0~100), 30이상이면 "특가" display
    private int price; // 가격
    private int deliveryFee; // 배송비, 0이면 "무료배송" display
    private String description; // 상품 설명 (추후 사진파일로 변경)
    private int stock; // 재고량, 0이면 sold out 상태
    // Todo: 혜택, 배송정보, 오늘의딜 여부 등 구현
}