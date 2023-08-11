package com.example.demo.firstinstance.controller.firstinstanceurl.form;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardApiDtoForm {

    private Long id;
    private String title;
    private String content;

    private String isDel;
    private LocalDateTime modifiedDate;
    private LocalDateTime createdDate;
    private Long customerId;
}