package com.example.prj3.mapper;

import com.example.prj3.domain.Board;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface BoardMapper {

    @Select("""
            SELECT id, title, writer, inserted
            FROM Board
            ORDER BY id DESC 
            """)
    List<Board> selectAll();

    @Select("""
            SELECT *
            FROM Board
            WHERE id = #{id}
            """)
    Board selectById(Integer id);
    @Update("""
            UPDATE Board
            SET title = #{title},
                body = #{body},
                writer = #{writer}
            WHERE id = #{id}
            """)
    int update(Board board);

    @Delete("""
            DELETE FROM Board
            WHERE id = #{id}
            """)
    int deleteById(Integer id);
}
