package com.green.jpaexam.product.model;

import com.green.jpaexam.config.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Table(name = "t_product")
@EqualsAndHashCode //동등성 jpa할 때는 필수
// 객체가 같으면 (주소값이 같은것) 동일성
// 데이터가 같으면 동등성
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment
    private Long number;

    @Column(nullable = false) //not null
    private String name;

    @Column(nullable = false) //not null
    private Integer price;

    @Column(nullable = false) //not null
    private Integer stock;

/*
    @CreationTimestamp
//    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
//    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
*/



}

