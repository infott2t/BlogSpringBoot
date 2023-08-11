package com.example.demo.domain.board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardRepositoryCustom {

    Page<BoardApiDto> searchAllV2(BoardSearchCondition condition, Pageable pageable);

    Page<BoardApiDto> searchAllV3(BoardSearchCondition2 condition, Pageable pageable);

  List<BoardApiDto> searchFindAllDesc();


}