package com.green.jpaexam.product;

import com.green.jpaexam.entity.ProductEntity;
import com.green.jpaexam.entity.QCategoryEntity;
import com.green.jpaexam.entity.QProductEntity;
import com.green.jpaexam.entity.QProviderEntity;
import com.green.jpaexam.product.model.ProductRes;
import com.green.jpaexam.product.model.ProductResQdsl;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QuerydslUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.green.jpaexam.entity.QCategoryEntity.categoryEntity;
import static com.green.jpaexam.entity.QProductDetailEntity.productDetailEntity;
import static com.green.jpaexam.entity.QProductEntity.productEntity;
import static com.green.jpaexam.entity.QProviderEntity.providerEntity;
import static org.apache.logging.log4j.ThreadContext.isEmpty;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductQdsl {
    private final JPAQueryFactory jpaQueryFactory;
    private final QProductEntity p = productEntity;
    private final QCategoryEntity c = categoryEntity;
    private final QProviderEntity pv = providerEntity;

    public List<ProductResQdsl> selProductAll(Pageable pageable) {

        JPQLQuery<ProductResQdsl> query = jpaQueryFactory.select(
                        Projections.bean(ProductResQdsl.class,
                                p.number, p.name, p.price, p.stock, p.createdAt, productDetailEntity.description,
                                pv.name.as("providerNm"), c.name.as("categoryNm")))
                .from(p)
                .join(p.productDetailEntity, productDetailEntity)
                .join(p.categoryEntity, c)
                .join(p.providerEntity, pv)
                .orderBy(p.number.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());


        return query.fetch();

    }

    private OrderSpecifier[] getAllOrderSpecifiers(Pageable pageable) {
        List<OrderSpecifier> orders = new LinkedList();
        if(!pageable.getSort().isEmpty()) {
            for(Sort.Order order : pageable.getSort()) {
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;

                switch(order.getProperty().toLowerCase()) {//사용자가 혹시 대문자로 입력했을 경우에 소문자로 변경해줌
                    case "number": orders.add(new OrderSpecifier(direction, p.number)); break;
                    case "product_name": orders.add(new OrderSpecifier(direction, p.name)); break;
                    case "price": orders.add(new OrderSpecifier(direction, p.price)); break;
                    case "category_name": orders.add(new OrderSpecifier(direction, c.name)); break;
                }
            }
        }
        return orders.stream().toArray(OrderSpecifier[]::new);
    }

}
