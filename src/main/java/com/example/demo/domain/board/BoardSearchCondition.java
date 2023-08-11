package com.example.demo.domain.board;
import lombok.Data;

@Data
public class BoardSearchCondition {

    private String field;
    private String s;

    private String sdate;
    private String edate;
}
