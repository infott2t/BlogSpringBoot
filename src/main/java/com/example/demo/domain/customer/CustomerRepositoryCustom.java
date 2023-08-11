package com.example.demo.domain.customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerRepositoryCustom {

    Page<CustomerApiDto> searchAllV2(CustomerSearchCondition condition, Pageable pageable);

    Page<CustomerApiDto> searchAllV3(CustomerSearchCondition2 condition, Pageable pageable);

  List<CustomerApiDto> searchFindAllDesc();


}