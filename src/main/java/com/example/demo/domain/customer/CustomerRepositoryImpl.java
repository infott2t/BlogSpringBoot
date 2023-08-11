package com.example.demo.domain.customer;
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

import static com.example.demo.domain.customer.QCustomer.customer;
import static org.springframework.util.StringUtils.hasText;



public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public CustomerRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


   @Override
    public Page<CustomerApiDto> searchAllV3(CustomerSearchCondition2 condition2, Pageable pageable) {

        List<CustomerApiDto> content = queryFactory.
                select(Projections.constructor(CustomerApiDto.class,
                        customer.id,
                        customer.name,
                        customer.isDel,
                        customer.modifiedDate,
                        customer.createdDate              )).from(customer)
                .where(
                        searchAllV3Predicate(condition2)
                ).where(customer.isDel.eq("N"))
                .orderBy(customer.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(customer.count())
                .from(customer)
                .where(
                        searchAllV3Predicate(condition2)
                ).where(customer.isDel.eq("N"))
                .fetch().get(0);

        return new PageImpl<>(content, pageable, total);
    }

    private Predicate searchAllV3Predicate(CustomerSearchCondition2 condition2) {
        return new BooleanBuilder()
                        .and(condId(condition2.getId()))
                        .and(condName(condition2.getName()))
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
            builder.or(customer.id.eq(Long.valueOf(id)));
        }
        return builder;
    }

    private Predicate condName(String name) {
        BooleanBuilder builder = new BooleanBuilder();
        if(hasText(name)){
            builder.or(customer.name.eq(name));
        }
        return builder;
    }

    private Predicate condIsDel(String isDel) {
        BooleanBuilder builder = new BooleanBuilder();
        if(hasText(isDel)){
            builder.or(customer.isDel.eq(isDel));
        }
        return builder;
    }

    private Predicate condModifiedDate(String modifiedDate) {
        BooleanBuilder builder = new BooleanBuilder();
        if(hasText(modifiedDate)){
            builder.or(customer.modifiedDate.eq(LocalDateTime.parse(modifiedDate)));
        }
        return builder;
    }

    private Predicate condCreatedDate(String createdDate) {
        BooleanBuilder builder = new BooleanBuilder();
        if(hasText(createdDate)){
            builder.or(customer.createdDate.eq(LocalDateTime.parse(createdDate)));
        }
        return builder;
    }


    private Predicate condS2(String field, String s) {
        BooleanBuilder builder = new BooleanBuilder();

        if(hasText(field) && hasText(s)){
            if(field.equals("id")){
                builder.or(customer.id.eq(Long.valueOf(s)));
            }
            if(field.equals("name")){
                builder.or(customer.name.like("%"+s+"%"));
            }
            if(field.equals("isDel")){
                builder.or(customer.isDel.like("%"+s+"%"));
            }
            if(field.equals("modifiedDate")){
                builder.or(customer.modifiedDate.eq(LocalDateTime.parse(s)));
            }
            if(field.equals("createdDate")){
                builder.or(customer.createdDate.eq(LocalDateTime.parse(s)));
            }
        }
        return builder;
    }

    private Predicate condSdate2(String sdate) {
        BooleanBuilder builder = new BooleanBuilder();

        if(hasText(sdate)){
            try {
                LocalDateTime localDateTime = LocalDateTime.parse(sdate + "T00:00:00");
                builder.or(customer.modifiedDate.goe(localDateTime)); // isrtDate >= sdate

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
                builder.or(customer.modifiedDate.loe(localDateTime)); // isrtDate <= edate

            } catch (DateTimeParseException e) {
            }
        }
        return builder;
    }


    @Override
    public Page<CustomerApiDto> searchAllV2(CustomerSearchCondition condition, Pageable pageable) {

        List<CustomerApiDto> content = queryFactory.
                select(Projections.constructor(CustomerApiDto.class,
                        customer.id,
                        customer.name,
                        customer.isDel,
                        customer.modifiedDate,
                        customer.createdDate              )).from(customer)
                .where(
                        searchAllV2Predicate(condition)
                ).where(customer.isDel.eq("N"))
                .orderBy(customer.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(customer.count())
                .from(customer)
                .where(
                        searchAllV2Predicate(condition)
                ).where(customer.isDel.eq("N"))
                .fetch().get(0);

        return new PageImpl<>(content, pageable, total);
    }





    private BooleanBuilder searchAllV2Predicate(CustomerSearchCondition condition){
        return new BooleanBuilder()
                .and(condS(condition.getField(), condition.getS()))
                .and(condSdate(condition.getSdate()))
                .and(condEdate(condition.getEdate()));

    }

    private Predicate condS(String field, String s) {
        BooleanBuilder builder = new BooleanBuilder();

        if(hasText(field) && hasText(s)){
            if(field.equals("id")){
                builder.or(customer.id.eq(Long.valueOf(s)));
            }
            if(field.equals("name")){
                builder.or(customer.name.like("%"+s+"%"));
            }
            if(field.equals("isDel")){
                builder.or(customer.isDel.like("%"+s+"%"));
            }
            if(field.equals("modifiedDate")){
                builder.or(customer.modifiedDate.eq(LocalDateTime.parse(s)));
            }
            if(field.equals("createdDate")){
                builder.or(customer.createdDate.eq(LocalDateTime.parse(s)));
            }
        }
        return builder;
    }

    private Predicate condSdate( String sdate){
        BooleanBuilder builder = new BooleanBuilder();

        if(hasText(sdate)){
            try {
                LocalDateTime localDateTime = LocalDateTime.parse(sdate + "T00:00:00");
                builder.or(customer.modifiedDate.goe(localDateTime)); // isrtDate >= sdate

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
                builder.or(customer.modifiedDate.loe(localDateTime)); // isrtDate <= edate

            } catch (DateTimeParseException e) {
            }
        }
        return builder;
    }



    @Override
    public List<CustomerApiDto> searchFindAllDesc() {
        List<CustomerApiDto> content = queryFactory.
                select(Projections.constructor(CustomerApiDto.class,
                        customer.id,
                        customer.name,
                        customer.isDel,
                        customer.modifiedDate,
                        customer.createdDate              )).from(customer).where(customer.isDel.eq("N"))
                .orderBy(customer.id.asc())
                .fetch();


        return content;
    }
}