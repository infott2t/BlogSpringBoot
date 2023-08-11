package com.example.demo.domain.customer;
import com.example.demo.domain.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>,
        QuerydslPredicateExecutor<Customer>, CustomerRepositoryCustom {


}