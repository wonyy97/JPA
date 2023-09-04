package com.green.jpaexam.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "t_category")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
@EqualsAndHashCode
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment
    @Column(updatable = false, nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private Long id;

    @Column(unique = true)
    private String code;

    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "categoryEntity") //mappedBy를 안주면 다대다가 아닌데 테이블이 하나 더 생긴다, 주인한테만 포린키 생성
    //주인이 아닌 엔터티에서 양방향을 걸 때 항상 줘야한다 그래야 쓸데없는 테이블이 안생기니까 , 아니면 아예 양방향을 걸지 않던가......
//    @JoinColumn(name = "provider_id")
    @ToString.Exclude //양방향일때는 얘가 필수
    private List<ProductEntity> productEntityList = new ArrayList();
}
