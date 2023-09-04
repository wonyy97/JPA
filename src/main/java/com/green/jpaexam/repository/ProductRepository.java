package com.green.jpaexam.repository;

import com.green.jpaexam.entity.ProductEntity;
import com.green.jpaexam.product.model.ProductRes;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query(" select new com.green.jpaexam.product.model.ProductRes" +
            " (p.number, p.name, p.price, p.stock, pv.name, j.name, d.description, p.createdAt) " +
            " from ProductEntity p join p.productDetailEntity d join CategoryEntity j on p.categoryEntity.id = j.id join p.providerEntity pv" +
            " where p.name = :productName and p.price >= :price")
    // where 절에서 이름 같으면 굳이 @Param 안줘도 된다
    List<ProductRes> selProductAll(Pageable page, String productName, int price);

    /*@Query("select d from ProductDetailEntity d join CategoryEntity c on d.productNumber = c.id")
    SELECT * FROM t_product_detail d
    LEFT JOIN t_category c
    ON d.product_number = c.id;*/
}
