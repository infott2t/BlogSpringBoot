package com.example.demo.domain.categorystr;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryStrRepositoryCustom {

    Page<CategoryStrApiDto> searchAllV2(CategoryStrSearchCondition condition, Pageable pageable);

    Page<CategoryStrApiDto> searchAllV3(CategoryStrSearchCondition2 condition, Pageable pageable);

  List<CategoryStrApiDto> searchFindAllDesc();


}