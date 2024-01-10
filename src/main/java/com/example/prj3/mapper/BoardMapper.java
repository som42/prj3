package com.example.prj3.mapper;

import com.example.prj3.domain.Board;
import org.apache.ibatis.annotations.*;

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

    @Insert("""
            INSERT INTO Board (title, body, writer)
            VALUES (#{title}, #{body}, #{writer})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id") //바로 입력 하자마자 키를 알고 싶을때
    int insert(Board board);

    @Select("""
            <script>
            <bind name= "pattern" value="'%' + search + '%'" />
            SELECT id, title, writer, inserted
            FROM Board
            WHERE
              title LIKE #{pattern}
            OR body LIKE #{pattern}
            OR writer LIKE #{pattern}
            ORDER BY id DESC 
            LIMIT #{startIndex}, #{rowPerPage}
            </script>
            """)
    List<Board> selectAllPaging(Integer startIndex, Integer rowPerPage, String search);

    @Select("""
            SELECT COUNT(*)
            FROM Board
            """)
    Integer countAll();
}
