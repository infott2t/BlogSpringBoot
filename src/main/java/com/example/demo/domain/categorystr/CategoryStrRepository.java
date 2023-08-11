package com.example.demo.domain.categorystr;
import com.example.demo.domain.categorystr.CategoryStr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryStrRepository extends JpaRepository<CategoryStr, Long>,
        QuerydslPredicateExecutor<CategoryStr>, CategoryStrRepositoryCustom {


}