package com.green.jpaexam.product;

import com.green.jpaexam.product.model.ProductEntity;
import com.green.jpaexam.product.model.ProductRes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}
