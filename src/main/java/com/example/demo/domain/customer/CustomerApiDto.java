package com.example.demo.domain.customer;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomerApiDto {
    private Long id;
    private String name;
    private String isDel;
    private LocalDateTime modifiedDate;
    private LocalDateTime createdDate;



    @QueryProjection
    public CustomerApiDto( Long id, String name, String isDel, LocalDateTime modifiedDate, LocalDateTime createdDate) {
        this.id = id;
        this.name = name;
        this.isDel = isDel;
        this.modifiedDate = modifiedDate;
        this.createdDate = createdDate;
    }

 

}