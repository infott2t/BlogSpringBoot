package com.example.demo.firstinstance.controller.firstinstanceurl.form;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CategoryStrApiDtoForm {

    private Long id;
    private String name;
    private Long refId;

    private String isDel;
    private LocalDateTime modifiedDate;
    private LocalDateTime createdDate;
}