package com.example.demo.domain.board;
import com.example.demo.domain.customer.Customer;
import com.example.demo.domain.categorystr.CategoryStr;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardApiDto {
    private Long id;
    private String title;
    private String content;
    private String isOpen;
    private String isDel;
    private LocalDateTime modifiedDate;
    private LocalDateTime createdDate;
    private Customer customer;
    private CategoryStr categoryStr;



    @QueryProjection
    public BoardApiDto( Long id, String title, String content, String isOpen, String isDel, LocalDateTime modifiedDate, LocalDateTime createdDate, Customer customer, CategoryStr categoryStr) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.isOpen = isOpen;
        this.isDel = isDel;
        this.modifiedDate = modifiedDate;
        this.createdDate = createdDate;
        this.customer = customer;
        this.categoryStr = categoryStr;
    }

 

}