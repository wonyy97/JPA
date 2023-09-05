package com.green.jpaexam.product.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
public class ProductResQdsl {
    private Long number;
    private String name;
    private int price;
    private int stock;
    private String providerNm;
    private String categoryNm;
    private String description;
    private LocalDateTime createdAt;

}
