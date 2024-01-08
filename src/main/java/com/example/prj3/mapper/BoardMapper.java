package com.example.prj3.mapper;

import com.example.prj3.domain.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BoardMapper {

    @Select("""
            SELECT id, title, writer, inserted
            FROM Board
            ORDER BY id DESC 
            """)
    List<Board> selectAll();
}
