package com.green.jpaexam.product;

import com.green.jpaexam.repository.CategoryRepository;
import com.green.jpaexam.repository.ProductDetailRepository;
import com.green.jpaexam.entity.CategoryEntity;
import com.green.jpaexam.entity.ProductDetailEntity;
import com.green.jpaexam.entity.ProviderEntity;
import com.green.jpaexam.product.model.ProductDto;
import com.green.jpaexam.entity.ProductEntity;
import com.green.jpaexam.product.model.ProductRes;
import com.green.jpaexam.product.model.ProductUpdDto;
import com.green.jpaexam.repository.ProductRepository;
import com.green.jpaexam.repository.ProviderRepository;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductDao dao;
    private final ProductRepository productRep;
    private final ProductDetailRepository productDetailRep;
    private final ProviderRepository providerRep;
    private final CategoryRepository categoryRep;

    public ProductRes saveProduct2(ProductDto dto) {
        ProviderEntity providerEntity = providerRep.findById(dto.getProviderId()).get();
        CategoryEntity categoryEntity = categoryRep.findById(dto.getCategoryId()).get();

        ProductDetailEntity productDetailEntity = ProductDetailEntity.builder()
                .description(dto.getDescription())
                .build();

        ProductEntity productEntity = ProductEntity.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .categoryEntity(categoryEntity)
                .providerEntity(providerEntity)
                .build();

        productEntity.setProductDetailEntity(productDetailEntity);
//        productEntity.setProductDetailEntity(productDetailEntity);
//        productDetailEntity.setProductEntity(productEntity);

        productRep.save(productEntity);
        return null;
    }


    public ProductRes saveProduct(ProductDto dto) {

        ProviderEntity providerEntity = providerRep.findById(dto.getProviderId()).get();
        CategoryEntity categoryEntity = categoryRep.findById(dto.getCategoryId()).get();

        ProductEntity productEntity = ProductEntity.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .stock(dto.getStock())

                .categoryEntity(categoryEntity)
                .providerEntity(providerEntity)
                .build();
        productRep.save(productEntity);
//        return dao.saveProduct(entity);
        //dao한테 보낼 때는 entity정도는 보내주도록 하자

        ProductDetailEntity productDetailEntity = ProductDetailEntity.builder()
                .productEntity(productEntity)
                .description(dto.getDescription())
                .build();
        productDetailRep.save(productDetailEntity);

        return ProductRes.builder()
                .number(productEntity.getNumber())
                .name(productEntity.getName())
                .price(productEntity.getPrice())
                .stock(productEntity.getStock())
                .categoryNm(productEntity.getCategoryEntity().getName())
                .providerNm(productEntity.getProviderEntity().getName())
                .description(productDetailEntity.getDescription())
//                .createdAt(productEntity.getCreatedAt().toString())
                .build();
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

    public List<ProductRes> getProductAllJpql(Pageable page) {
        List<ProductRes> list = productRep.selProductAll(page, "이름1", 30000);
//        for (ProductRes e : list) {
//            log.info("item : {}", list);
//        }
        return list;
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
