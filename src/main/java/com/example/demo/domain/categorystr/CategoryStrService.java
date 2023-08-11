package com.example.demo.domain.categorystr;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryStrService {

    private final CategoryStrRepository categoryStrRepository;
    
    @Transactional(readOnly = true)
    public List<CategoryStrApiDto> searchFindAllDesc() {
        return  categoryStrRepository.searchFindAllDesc();
    }

    @Transactional(readOnly = true)
    public CategoryStr findById(Long id) {
        return categoryStrRepository.findById(id).orElseThrow();
    }

    @Transactional
    public void save(CategoryStr categoryStr) {
        categoryStrRepository.save(categoryStr);
    }

    @Transactional(readOnly = true)
    public Page<CategoryStrApiDto> searchAllV2(CategoryStrSearchCondition condition, Pageable pageable) {
        return categoryStrRepository.searchAllV2(condition, pageable);
    }
}