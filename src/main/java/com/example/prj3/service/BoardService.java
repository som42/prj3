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
}