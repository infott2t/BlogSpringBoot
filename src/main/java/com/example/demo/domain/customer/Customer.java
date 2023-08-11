package com.example.demo.domain.customer;

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
@Table(name="CUSTOMER")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_ID")
    private Long id;

    private String name;
    private String isDel;
    private LocalDateTime modifiedDate;
    private LocalDateTime createdDate;



    @Builder
    public Customer(Long id, String name, String isDel, LocalDateTime modifiedDate, LocalDateTime createdDate) {
        this.id = id;
        this.name = name;
        this.isDel = isDel;
        this.modifiedDate = modifiedDate;
        this.createdDate = createdDate;
    }
}