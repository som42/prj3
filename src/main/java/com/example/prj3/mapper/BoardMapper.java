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
            SELECT b.id, b.title, b.body, b.inserted, b.writer, f.fileName
            FROM Board b 
            LEFT JOIN FileName f 
            ON b.id = f.boardId
            WHERE b.id = #{id}
            """)
//    resultMap 을 사용하라는 의미가 @ResultMap 명시해주면 된다.
    @ResultMap("boardResultMap ")
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
            SELECT b.id, b.title, b.writer, b.inserted, COUNT(f.id) fileCount
            FROM Board b
            LEFT JOIN FileName f
            ON b.id = f.boardId
            
            <where>
                <if test="(type eq 'all') or (type eq 'title')">
                  title LIKE #{pattern}
                </if>
                <if test="(type eq 'all') or (type eq 'body')">
                OR body LIKE #{pattern}
                </if>
                <if test="(type eq 'all') or (type eq 'writer')">
                OR writer LIKE #{pattern}
                </if>
           </where>
           
            GROUP BY b.id
            ORDER BY b.id DESC 
            LIMIT #{startIndex}, #{rowPerPage}
            </script>
            """)
    List<Board> selectAllPaging(Integer startIndex, Integer rowPerPage, String search, String type);

    @Select("""
            <script>
            <bind name= "pattern" value="'%' + search + '%'" />
            SELECT COUNT(*)
            FROM Board
            
            <where>
                <if test="(type eq 'all') or (type eq 'title')">
                  title LIKE #{pattern}
                </if>
                <if test="(type eq 'all') or (type eq 'body')">
                OR body LIKE #{pattern}
                </if>
                <if test="(type eq 'all') or (type eq 'writer')">
                OR writer LIKE #{pattern}
                </if>
           </where>
            </script>
            """)
    Integer countAll(String search, String type);

    @Insert("""
            INSERT INTO FileName (boardId, fileName)
            VALUES (#{boardId}, #{fileName})
            """)
    Integer insertFileName(Integer boardId, String fileName);

    @Select("""
            SELECT fileName 
            FROM FileName
            WHERE boardId = #{boardId}
            """)
    List<String> selectFileNameByBoardId(Integer boardId);

    @Delete("""
            DELETE FROM FileName
            WHERE boardId = #{boardId}
            """)
    void deleteFileNameByBoardId(Integer boardId);
}
