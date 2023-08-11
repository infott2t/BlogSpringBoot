package com.example.demo.domain.categorystr;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

import static com.example.demo.domain.categorystr.QCategoryStr.categoryStr;
import static org.springframework.util.StringUtils.hasText;



public class CategoryStrRepositoryImpl implements CategoryStrRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public CategoryStrRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


   @Override
    public Page<CategoryStrApiDto> searchAllV3(CategoryStrSearchCondition2 condition2, Pageable pageable) {

        List<CategoryStrApiDto> content = queryFactory.
                select(Projections.constructor(CategoryStrApiDto.class,
                        categoryStr.id,
                        categoryStr.name,
                        categoryStr.refId,
                        categoryStr.isDel,
                        categoryStr.modifiedDate,
                        categoryStr.createdDate              )).from(categoryStr)
                .where(
                        searchAllV3Predicate(condition2)
                ).where(categoryStr.isDel.eq("N"))
                .orderBy(categoryStr.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(categoryStr.count())
                .from(categoryStr)
                .where(
                        searchAllV3Predicate(condition2)
                ).where(categoryStr.isDel.eq("N"))
                .fetch().get(0);

        return new PageImpl<>(content, pageable, total);
    }

    private Predicate searchAllV3Predicate(CategoryStrSearchCondition2 condition2) {
        return new BooleanBuilder()
                        .and(condId(condition2.getId()))
                        .and(condName(condition2.getName()))
                        .and(condRefId(condition2.getRefId()))
                        .and(condIsDel(condition2.getIsDel()))
                        .and(condModifiedDate(condition2.getModifiedDate()))
                        .and(condCreatedDate(condition2.getCreatedDate()))
                .and(condS2(condition2.getField(), condition2.getS()))
                .and(condSdate2(condition2.getSdate()))
                .and(condEdate2(condition2.getEdate()));
    }




    private Predicate condId(String id) {
        BooleanBuilder builder = new BooleanBuilder();
        if(hasText(id)){
            builder.or(categoryStr.id.eq(Long.valueOf(id)));
        }
        return builder;
    }

    private Predicate condName(String name) {
        BooleanBuilder builder = new BooleanBuilder();
        if(hasText(name)){
            builder.or(categoryStr.name.eq(name));
        }
        return builder;
    }

    private Predicate condRefId(String refId) {
        BooleanBuilder builder = new BooleanBuilder();
        if(hasText(refId)){
            builder.or(categoryStr.refId.eq(Long.valueOf(refId)));
        }
        return builder;
    }

    private Predicate condIsDel(String isDel) {
        BooleanBuilder builder = new BooleanBuilder();
        if(hasText(isDel)){
            builder.or(categoryStr.isDel.eq(isDel));
        }
        return builder;
    }

    private Predicate condModifiedDate(String modifiedDate) {
        BooleanBuilder builder = new BooleanBuilder();
        if(hasText(modifiedDate)){
            builder.or(categoryStr.modifiedDate.eq(LocalDateTime.parse(modifiedDate)));
        }
        return builder;
    }

    private Predicate condCreatedDate(String createdDate) {
        BooleanBuilder builder = new BooleanBuilder();
        if(hasText(createdDate)){
            builder.or(categoryStr.createdDate.eq(LocalDateTime.parse(createdDate)));
        }
        return builder;
    }


    private Predicate condS2(String field, String s) {
        BooleanBuilder builder = new BooleanBuilder();

        if(hasText(field) && hasText(s)){
            if(field.equals("id")){
                builder.or(categoryStr.id.eq(Long.valueOf(s)));
            }
            if(field.equals("name")){
                builder.or(categoryStr.name.like("%"+s+"%"));
            }
            if(field.equals("refId")){
                builder.or(categoryStr.refId.eq(Long.valueOf(s)));
            }
            if(field.equals("isDel")){
                builder.or(categoryStr.isDel.like("%"+s+"%"));
            }
            if(field.equals("modifiedDate")){
                builder.or(categoryStr.modifiedDate.eq(LocalDateTime.parse(s)));
            }
            if(field.equals("createdDate")){
                builder.or(categoryStr.createdDate.eq(LocalDateTime.parse(s)));
            }
        }
        return builder;
    }

    private Predicate condSdate2(String sdate) {
        BooleanBuilder builder = new BooleanBuilder();

        if(hasText(sdate)){
            try {
                LocalDateTime localDateTime = LocalDateTime.parse(sdate + "T00:00:00");
                builder.or(categoryStr.modifiedDate.goe(localDateTime)); // isrtDate >= sdate

            } catch (DateTimeParseException e) {
            }
        }
        return builder;
    }
    private Predicate condEdate2(String edate) {
        BooleanBuilder builder = new BooleanBuilder();
        if(hasText(edate)) {
            try {
                LocalDateTime localDateTime = LocalDateTime.parse(edate + "T00:00:00");
                builder.or(categoryStr.modifiedDate.loe(localDateTime)); // isrtDate <= edate

            } catch (DateTimeParseException e) {
            }
        }
        return builder;
    }


    @Override
    public Page<CategoryStrApiDto> searchAllV2(CategoryStrSearchCondition condition, Pageable pageable) {

        List<CategoryStrApiDto> content = queryFactory.
                select(Projections.constructor(CategoryStrApiDto.class,
                        categoryStr.id,
                        categoryStr.name,
                        categoryStr.refId,
                        categoryStr.isDel,
                        categoryStr.modifiedDate,
                        categoryStr.createdDate              )).from(categoryStr)
                .where(
                        searchAllV2Predicate(condition)
                ).where(categoryStr.isDel.eq("N"))
                .orderBy(categoryStr.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(categoryStr.count())
                .from(categoryStr)
                .where(
                        searchAllV2Predicate(condition)
                ).where(categoryStr.isDel.eq("N"))
                .fetch().get(0);

        return new PageImpl<>(content, pageable, total);
    }





    private BooleanBuilder searchAllV2Predicate(CategoryStrSearchCondition condition){
        return new BooleanBuilder()
                .and(condS(condition.getField(), condition.getS()))
                .and(condSdate(condition.getSdate()))
                .and(condEdate(condition.getEdate()));

    }

    private Predicate condS(String field, String s) {
        BooleanBuilder builder = new BooleanBuilder();

        if(hasText(field) && hasText(s)){
            if(field.equals("id")){
                builder.or(categoryStr.id.eq(Long.valueOf(s)));
            }
            if(field.equals("name")){
                builder.or(categoryStr.name.like("%"+s+"%"));
            }
            if(field.equals("refId")){
                builder.or(categoryStr.refId.eq(Long.valueOf(s)));
            }
            if(field.equals("isDel")){
                builder.or(categoryStr.isDel.like("%"+s+"%"));
            }
            if(field.equals("modifiedDate")){
                builder.or(categoryStr.modifiedDate.eq(LocalDateTime.parse(s)));
            }
            if(field.equals("createdDate")){
                builder.or(categoryStr.createdDate.eq(LocalDateTime.parse(s)));
            }
        }
        return builder;
    }

    private Predicate condSdate( String sdate){
        BooleanBuilder builder = new BooleanBuilder();

        if(hasText(sdate)){
            try {
                LocalDateTime localDateTime = LocalDateTime.parse(sdate + "T00:00:00");
                builder.or(categoryStr.modifiedDate.goe(localDateTime)); // isrtDate >= sdate

            } catch (DateTimeParseException e) {
            }
        }
        return builder;
    }

    private Predicate condEdate( String edate){
        BooleanBuilder builder = new BooleanBuilder();
        if(hasText(edate)) {
            try {
                LocalDateTime localDateTime = LocalDateTime.parse(edate + "T00:00:00");
                builder.or(categoryStr.modifiedDate.loe(localDateTime)); // isrtDate <= edate

            } catch (DateTimeParseException e) {
            }
        }
        return builder;
    }



    @Override
    public List<CategoryStrApiDto> searchFindAllDesc() {
        List<CategoryStrApiDto> content = queryFactory.
                select(Projections.constructor(CategoryStrApiDto.class,
                        categoryStr.id,
                        categoryStr.name,
                        categoryStr.refId,
                        categoryStr.isDel,
                        categoryStr.modifiedDate,
                        categoryStr.createdDate              )).from(categoryStr).where(categoryStr.isDel.eq("N"))
                .orderBy(categoryStr.id.asc())
                .fetch();


        return content;
    }
}