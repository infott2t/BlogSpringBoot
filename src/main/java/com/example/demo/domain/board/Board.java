package com.example.demo.domain.board;

import com.example.demo.domain.customer.Customer;
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
    private String content;
    private String isDel;
    private LocalDateTime modifiedDate;
    private LocalDateTime createdDate;

    @ManyToOne(targetEntity = Customer.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;



    @Builder
    public Board(Long id, String title, String content, String isDel, LocalDateTime modifiedDate, LocalDateTime createdDate, Customer customer) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.isDel = isDel;
        this.modifiedDate = modifiedDate;
        this.createdDate = createdDate;
        this.customer = customer;
    }
}