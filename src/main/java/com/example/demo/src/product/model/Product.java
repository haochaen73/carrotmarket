package com.example.demo.src.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter // 해당 클래스에 대한 접근자 생성
@Setter // 해당 클래스에 대한 설정자 생성
@AllArgsConstructor
public class Product {
    private int productIdx;
    private int userIdx;
    private int categoryIdx;
    private String productName;
    private int price;
    private String productDetail;
    private String productMainImg;
}
