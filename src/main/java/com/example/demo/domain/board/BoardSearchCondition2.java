package com.example.demo.domain.board;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardSearchCondition2 {

    private String id;
    private String title;
    private String content;
    private String isOpen;
    private String isDel;
    private String modifiedDate;
    private String createdDate;
    private String customerId;
    private String categoryStrId;
   private String field;
   private String s;
   private String sdate;
   private String edate;
}