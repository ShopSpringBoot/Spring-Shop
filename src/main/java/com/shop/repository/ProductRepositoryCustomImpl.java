package com.shop.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.constant.ProductSellStatus;
import com.shop.dto.ProductSearchDto;
import com.shop.entity.Product;
import com.shop.entity.QProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    private JPAQueryFactory queryFactory;

    public ProductRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    private BooleanExpression searchSellStatusEq(ProductSellStatus searchSellStatus) {
        return searchSellStatus == null ? null : QProduct.product.productSellStatus.eq(searchSellStatus);
    }

    private BooleanExpression regDtsAfter(String searchDateType) {
        LocalDateTime dateTime = LocalDateTime.now();

        if (StringUtils.equals("all", searchDateType) || searchDateType == null) {
            return null;
        } else if (StringUtils.equals("1d", searchDateType)) {
            dateTime = dateTime.minusDays(1);
        } else if (StringUtils.equals("1w", searchDateType)) {
            dateTime = dateTime.minusWeeks(1);
        } else if (StringUtils.equals("1m", searchDateType)) {
            dateTime = dateTime.minusMonths(1);
        } else if (StringUtils.equals("6m", searchDateType)) {
            dateTime = dateTime.minusMonths(6);
        }

        return QProduct.product.regTime.after(dateTime);
    }

    private BooleanExpression searchByLike(String searchBy, String searchQuery) {
        if (StringUtils.equals("productNm", searchBy)) {
            return QProduct.product.productNm.like("%" + searchQuery + "%");
        }

        return null;
    }

    @Override
    public Page<Product> getAdminProductPage(ProductSearchDto productSearchDto, Pageable pageable) {

        List<Product> content = queryFactory
                .selectFrom(QProduct.product)
                .where(regDtsAfter(productSearchDto.getSearchDateType()),
                        searchSellStatusEq(productSearchDto.getSearchSellStatus()),
                        searchByLike(productSearchDto.getSearchBy(),
                                productSearchDto.getSearchQuery()))
                .orderBy(QProduct.product.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory.select(Wildcard.count).from(QProduct.product)
                .where(regDtsAfter(productSearchDto.getSearchDateType()),
                        searchSellStatusEq(productSearchDto.getSearchSellStatus()),
                        searchByLike(productSearchDto.getSearchBy(), productSearchDto.getSearchQuery()))
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }
}
