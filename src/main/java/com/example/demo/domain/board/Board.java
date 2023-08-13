package com.example.demo.domain.board;

import com.example.demo.domain.customer.Customer;
import com.example.demo.domain.categorystr.CategoryStr;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="BOARD")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_ID")
    private Long id;

    private String title;
    @Column(length=10000)
    private String content;
    private String isOpen;
    private String isDel;
    private LocalDateTime modifiedDate;
    private LocalDateTime createdDate;

    @ManyToOne(targetEntity = Customer.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    @ManyToOne(targetEntity = CategoryStr.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_STR_ID")
    private CategoryStr categoryStr;



    @Builder
    public Board(Long id, String title, String content, String isOpen, String isDel, LocalDateTime modifiedDate, LocalDateTime createdDate, Customer customer, CategoryStr categoryStr) {
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