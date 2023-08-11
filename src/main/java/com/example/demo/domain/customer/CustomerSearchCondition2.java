package com.example.demo.domain.customer;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomerSearchCondition2 {

    private String id;
    private String name;
    private String isDel;
    private String modifiedDate;
    private String createdDate;
   private String field;
   private String s;
   private String sdate;
   private String edate;
}