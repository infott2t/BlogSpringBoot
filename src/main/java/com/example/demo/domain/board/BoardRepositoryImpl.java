package com.example.demo.domain.board;
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

import static com.example.demo.domain.board.QBoard.board;
import static com.example.demo.domain.customer.QCustomer.customer;
import static com.example.demo.domain.categorystr.QCategoryStr.categoryStr;
import static org.springframework.util.StringUtils.hasText;



public class BoardRepositoryImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public BoardRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


   @Override
    public Page<BoardApiDto> searchAllV3(BoardSearchCondition2 condition2, Pageable pageable) {

        List<BoardApiDto> content = queryFactory.
                select(Projections.constructor(BoardApiDto.class,
                        board.id,
                        board.title,
                        board.content,
                        board.isOpen,
                        board.isDel,
                        board.modifiedDate,
                        board.createdDate,
                        board.customer,
                        board.categoryStr              )).from(board)
                        .leftJoin(board.customer, customer)
                        .leftJoin(board.categoryStr, categoryStr)
                .where(
                        searchAllV3Predicate(condition2)
                ).where(board.isDel.eq("N"))
                .orderBy(board.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(board.count())
                .from(board)
                .where(
                        searchAllV3Predicate(condition2)
                ).where(board.isDel.eq("N"))
                .fetch().get(0);

        return new PageImpl<>(content, pageable, total);
    }

    private Predicate searchAllV3Predicate(BoardSearchCondition2 condition2) {
        return new BooleanBuilder()
                        .and(condId(condition2.getId()))
                        .and(condTitle(condition2.getTitle()))
                        .and(condContent(condition2.getContent()))
                        .and(condIsOpen(condition2.getIsOpen()))
                        .and(condIsDel(condition2.getIsDel()))
                        .and(condModifiedDate(condition2.getModifiedDate()))
                        .and(condCreatedDate(condition2.getCreatedDate()))
                        .and(condCustomerId(condition2.getCustomerId()))
                        .and(condCategoryStrId(condition2.getCategoryStrId()))
                .and(condS2(condition2.getField(), condition2.getS()))
                .and(condSdate2(condition2.getSdate()))
                .and(condEdate2(condition2.getEdate()));
    }




    private Predicate condId(String id) {
        BooleanBuilder builder = new BooleanBuilder();
        if(hasText(id)){
            builder.or(board.id.eq(Long.valueOf(id)));
        }
        return builder;
    }

    private Predicate condTitle(String title) {
        BooleanBuilder builder = new BooleanBuilder();
        if(hasText(title)){
            builder.or(board.title.eq(title));
        }
        return builder;
    }

    private Predicate condContent(String content) {
        BooleanBuilder builder = new BooleanBuilder();
        if(hasText(content)){
            builder.or(board.content.eq(content));
        }
        return builder;
    }

    private Predicate condIsOpen(String isOpen) {
        BooleanBuilder builder = new BooleanBuilder();
        if(hasText(isOpen)){
            builder.or(board.isOpen.eq(isOpen));
        }
        return builder;
    }

    private Predicate condIsDel(String isDel) {
        BooleanBuilder builder = new BooleanBuilder();
        if(hasText(isDel)){
            builder.or(board.isDel.eq(isDel));
        }
        return builder;
    }

    private Predicate condModifiedDate(String modifiedDate) {
        BooleanBuilder builder = new BooleanBuilder();
        if(hasText(modifiedDate)){
            builder.or(board.modifiedDate.eq(LocalDateTime.parse(modifiedDate)));
        }
        return builder;
    }

    private Predicate condCreatedDate(String createdDate) {
        BooleanBuilder builder = new BooleanBuilder();
        if(hasText(createdDate)){
            builder.or(board.createdDate.eq(LocalDateTime.parse(createdDate)));
        }
        return builder;
    }

    private Predicate condCustomerId (String customerId) {
        BooleanBuilder builder = new BooleanBuilder();
        if(hasText(customerId)){
            builder.or(board.customer.id.eq(Long.valueOf(customerId)));
        }
        return builder;
    }

    private Predicate condCategoryStrId (String categoryStrId) {
        BooleanBuilder builder = new BooleanBuilder();
        if(hasText(categoryStrId)){
            builder.or(board.categoryStr.id.eq(Long.valueOf(categoryStrId)));
        }
        return builder;
    }


    private Predicate condS2(String field, String s) {
        BooleanBuilder builder = new BooleanBuilder();

        if(hasText(field) && hasText(s)){
            if(field.equals("id")){
                builder.or(board.id.eq(Long.valueOf(s)));
            }
            if(field.equals("title")){
                builder.or(board.title.like("%"+s+"%"));
            }
            if(field.equals("content")){
                builder.or(board.content.like("%"+s+"%"));
            }
            if(field.equals("isOpen")){
                builder.or(board.isOpen.like("%"+s+"%"));
            }
            if(field.equals("isDel")){
                builder.or(board.isDel.like("%"+s+"%"));
            }
            if(field.equals("modifiedDate")){
                builder.or(board.modifiedDate.eq(LocalDateTime.parse(s)));
            }
            if(field.equals("createdDate")){
                builder.or(board.createdDate.eq(LocalDateTime.parse(s)));
            }
            if(field.equals("customerId")){
                builder.or(board.customer.id.eq(Long.valueOf(s)));
            }
            if(field.equals("categoryStrId")){
                builder.or(board.categoryStr.id.eq(Long.valueOf(s)));
            }
        }
        return builder;
    }

    private Predicate condSdate2(String sdate) {
        BooleanBuilder builder = new BooleanBuilder();

        if(hasText(sdate)){
            try {
                LocalDateTime localDateTime = LocalDateTime.parse(sdate + "T00:00:00");
                builder.or(board.modifiedDate.goe(localDateTime)); // isrtDate >= sdate

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
                builder.or(board.modifiedDate.loe(localDateTime)); // isrtDate <= edate

            } catch (DateTimeParseException e) {
            }
        }
        return builder;
    }


    @Override
    public Page<BoardApiDto> searchAllV2(BoardSearchCondition condition, Pageable pageable) {

        List<BoardApiDto> content = queryFactory.
                select(Projections.constructor(BoardApiDto.class,
                        board.id,
                        board.title,
                        board.content,
                        board.isOpen,
                        board.isDel,
                        board.modifiedDate,
                        board.createdDate,
                        board.customer,
                        board.categoryStr              )).from(board)
                        .leftJoin(board.customer, customer)
                        .leftJoin(board.categoryStr, categoryStr)
                .where(
                        searchAllV2Predicate(condition)
                ).where(board.isDel.eq("N"))
                .orderBy(board.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(board.count())
                .from(board)
                .where(
                        searchAllV2Predicate(condition)
                ).where(board.isDel.eq("N"))
                .fetch().get(0);

        return new PageImpl<>(content, pageable, total);
    }





    private BooleanBuilder searchAllV2Predicate(BoardSearchCondition condition){
        return new BooleanBuilder()
                .and(condS(condition.getField(), condition.getS()))
                .and(condSdate(condition.getSdate()))
                .and(condEdate(condition.getEdate()));

    }

    private Predicate condS(String field, String s) {
        BooleanBuilder builder = new BooleanBuilder();

        if(hasText(field) && hasText(s)){
            if(field.equals("id")){
                builder.or(board.id.eq(Long.valueOf(s)));
            }
            if(field.equals("title")){
                builder.or(board.title.like("%"+s+"%"));
            }
            if(field.equals("content")){
                builder.or(board.content.like("%"+s+"%"));
            }
            if(field.equals("isOpen")){
                builder.or(board.isOpen.like("%"+s+"%"));
            }
            if(field.equals("isDel")){
                builder.or(board.isDel.like("%"+s+"%"));
            }
            if(field.equals("modifiedDate")){
                builder.or(board.modifiedDate.eq(LocalDateTime.parse(s)));
            }
            if(field.equals("createdDate")){
                builder.or(board.createdDate.eq(LocalDateTime.parse(s)));
            }
            if(field.equals("customerId")){
                builder.or(board.customer.id.eq(Long.valueOf(s)));
            }
            if(field.equals("categoryStrId")){
                builder.or(board.categoryStr.id.eq(Long.valueOf(s)));
            }
        }
        return builder;
    }

    private Predicate condSdate( String sdate){
        BooleanBuilder builder = new BooleanBuilder();

        if(hasText(sdate)){
            try {
                LocalDateTime localDateTime = LocalDateTime.parse(sdate + "T00:00:00");
                builder.or(board.modifiedDate.goe(localDateTime)); // isrtDate >= sdate

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
                builder.or(board.modifiedDate.loe(localDateTime)); // isrtDate <= edate

            } catch (DateTimeParseException e) {
            }
        }
        return builder;
    }



    @Override
    public List<BoardApiDto> searchFindAllDesc() {
        List<BoardApiDto> content = queryFactory.
                select(Projections.constructor(BoardApiDto.class,
                        board.id,
                        board.title,
                        board.content,
                        board.isOpen,
                        board.isDel,
                        board.modifiedDate,
                        board.createdDate,
                        board.customer,
                        board.categoryStr              )).from(board).where(board.isDel.eq("N"))
                        .leftJoin(board.customer, customer)
                        .leftJoin(board.categoryStr, categoryStr)
                .orderBy(board.id.asc())
                .fetch();


        return content;
    }
}