package com.example.demo.firstinstance.controller.firstinstanceurl.form;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomerApiDtoForm {

    private Long id;
    private String name;

    private String isDel;
    private LocalDateTime modifiedDate;
    private LocalDateTime createdDate;
}