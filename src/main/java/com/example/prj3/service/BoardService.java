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

//    public List<Board> listBoard() {
////        List<Board> list = mapper.selectAll();
////        return list;
////    }

    public List<Board> listBoard(Integer page){
        Integer startIndex = (page - 1) * 10; //한페이지에 몇개 보여줄지 곱하면된다.
        // 게시물 목록
        return mapper.selectAllPaging(startIndex);
        // 페이지네이션이 필요한 정보
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

    public boolean addBoard(Board board) {
        int cnt = mapper.insert(board);
        return cnt == 1;
    }
}
