package com.example.demo.domain.categorystr;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CategoryStrApiDto {
    private Long id;
    private String name;
    private Long refId;
    private String isDel;
    private LocalDateTime modifiedDate;
    private LocalDateTime createdDate;



    @QueryProjection
    public CategoryStrApiDto( Long id, String name, Long refId, String isDel, LocalDateTime modifiedDate, LocalDateTime createdDate) {
        this.id = id;
        this.name = name;
        this.refId = refId;
        this.isDel = isDel;
        this.modifiedDate = modifiedDate;
        this.createdDate = createdDate;
    }

 

}