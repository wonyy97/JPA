package com.green.jpaexam.product;

import com.green.jpaexam.product.model.ProductDto;
import com.green.jpaexam.product.model.ProductEntity;
import com.green.jpaexam.product.model.ProductRes;
import com.green.jpaexam.product.model.ProductUpdDto;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.OrderBy;
import org.springdoc.core.converters.models.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductDao dao;

    public ProductRes saveProduct(ProductDto dto) {
                ProductEntity entity = ProductEntity.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .build();
        return dao.saveProduct(entity);
        //dao한테 보낼 때는 entity정도는 보내주도록 하자
    }

    public Page<ProductRes> getProductAll(Pageable page) {
        return dao.getProductAll(page);
//        List<ProductEntity> list = dao.getProductAll();
//        List<ProductRes> result = list.stream().map(item ->
//                ProductRes.builder()
//                        .number(item.getNumber())
//                        .name(item.getName())
//                        .price(item.getPrice())
//                        .stock(item.getStock())
//                        .build()
//        ).toList();
//        return result;
    }


    public ProductRes getProduct(Long number) {
        return dao.getProduct(number);
    }

    public ProductRes updProduct(ProductUpdDto dto) {
        ProductEntity entity = ProductEntity.builder()
                .number(dto.getNumber())
                .name(dto.getName())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .build();


        return dao.updProduct(entity);
    }

    public void delProduct(Long number) {
        dao.delProduct(number);
    }
}
