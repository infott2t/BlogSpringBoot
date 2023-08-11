package com.example.demo.domain.board;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    
    @Transactional(readOnly = true)
    public List<BoardApiDto> searchFindAllDesc() {
        return  boardRepository.searchFindAllDesc();
    }

    @Transactional(readOnly = true)
    public Board findById(Long id) {
        return boardRepository.findById(id).orElseThrow();
    }

    @Transactional
    public void save(Board board) {
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public Page<BoardApiDto> searchAllV2(BoardSearchCondition condition, Pageable pageable) {
        return boardRepository.searchAllV2(condition, pageable);
    }
}