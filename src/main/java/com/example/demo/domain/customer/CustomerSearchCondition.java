package com.example.demo.domain.customer;
import lombok.Data;

@Data
public class CustomerSearchCondition {

    private String field;
    private String s;

    private String sdate;
    private String edate;
}
