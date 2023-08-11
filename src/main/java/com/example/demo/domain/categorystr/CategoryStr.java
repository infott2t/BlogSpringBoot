package com.example.demo.domain.categorystr;

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
@Table(name="CATEGORY_STR")
public class CategoryStr {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_STR_ID")
    private Long id;

    private String name;
    private Long refId;
    private String isDel;
    private LocalDateTime modifiedDate;
    private LocalDateTime createdDate;



    @Builder
    public CategoryStr(Long id, String name, Long refId, String isDel, LocalDateTime modifiedDate, LocalDateTime createdDate) {
        this.id = id;
        this.name = name;
        this.refId = refId;
        this.isDel = isDel;
        this.modifiedDate = modifiedDate;
        this.createdDate = createdDate;
    }
}