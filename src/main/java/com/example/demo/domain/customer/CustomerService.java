package com.example.demo.domain.customer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    
    @Transactional(readOnly = true)
    public List<CustomerApiDto> searchFindAllDesc() {
        return  customerRepository.searchFindAllDesc();
    }

    @Transactional(readOnly = true)
    public Customer findById(Long id) {
        return customerRepository.findById(id).orElseThrow();
    }

    @Transactional
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Transactional(readOnly = true)
    public Page<CustomerApiDto> searchAllV2(CustomerSearchCondition condition, Pageable pageable) {
        return customerRepository.searchAllV2(condition, pageable);
    }
}