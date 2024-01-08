package com.example.prj3.service;

import com.example.prj3.domain.Board;
import com.example.prj3.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardMapper mapper;

    public List<Board> listBoard() {
        List<Board> list = mapper.selectAll();
        return list;
    }

    public Board getBoard(Integer id) {
        return mapper.selectById(id);
    }

    public boolean modify(Board board) {
        int cnt = mapper.update(board);

        return cnt == 1;
    }

    public boolean remove(Integer id) {
        int cnt = mapper.deleteById(id);
        return cnt == 1;
    }
}
