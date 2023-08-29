package com.green.jpaexam.entity;

import com.green.jpaexam.config.BaseEntity;
import com.green.jpaexam.product.model.ProductRes;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@Table(name = "t_product_detail")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ProductDetailEntity extends BaseEntity {

    @Id
    private Long number;

    @OneToOne
    @MapsId
    @JoinColumn(name = "product_number", columnDefinition = "BIGINT UNSIGNED")
    private ProductEntity productEntity;

    private String description;

}
