package com.example.prj3.service;

import com.example.prj3.domain.Board;
import com.example.prj3.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class BoardService {

    @Autowired
    private BoardMapper mapper;

    public Map<String, Object> listBoard(Integer page, String search, String type){
        //페이지당 행의 수
        Integer rowPerPage = 10;

        // 쿼리 LIMIT 절에 사용할 시작 인덱스
        Integer startIndex = (page - 1) * rowPerPage; //한페이지에 몇개 보여줄지 곱하면된다.

        // 페이지네이션이 필요한 정보
        Integer numOfRecords = mapper.countAll(search, type); //전체 레코드 갯수

        // 마지막 페이지 번호
        Integer lastPageNumber = (numOfRecords - 1) / rowPerPage + 1;

        // 페이지네이션 왼쪽 번호
        Integer leftPageNum = page - 5;
        // 1보다 작을 수 없음 (둘 중에 큰걸로 결정하면된다)
        leftPageNum = Math.max(leftPageNum, 1);

        // 페이지네이션 오른쪽 번호
        Integer rightPageNum = page + 9;
        // 마지막 페이지보다 클 수 없음
        rightPageNum = Math.min(rightPageNum, lastPageNumber);

        Map<String, Object> pageInfo = new HashMap<>();
        pageInfo.put("rightPageNum", rightPageNum);
        pageInfo.put("leftPageNum", leftPageNum);
        pageInfo.put("currentPageNum", page);
        pageInfo.put("lastPageNum", lastPageNumber);

        // 게시물 목록
        List<Board> list =  mapper.selectAllPaging(startIndex, rowPerPage, search, type);

        return Map.of("pageInfo", pageInfo, "boardList", list);
    }

    public Board getBoard(Integer id) {
        return mapper.selectById(id);
    }

    public boolean modify(Board board) {
        int cnt = mapper.update(board);

        return cnt == 1;
    }

    public boolean remove(Integer id) {

        // 파일명 조회
        List<String> fileNames = mapper.selectFileNameByBoardId(id);

        // FileName 테이블의 데이터 지우기
        mapper.deleteFileNameByBoardId(id);

        // 하드디스크의 파일 지우기
        for (String fileName : fileNames) {
            String path = "C:\\study\\upload\\" + id + "\\" + fileName;
            File file = new File(path);
            if (file.exists()){
                file.delete();
            }
        }

        //게시물 테이블의 데이터 지우기
        int cnt = mapper.deleteById(id);
        return cnt == 1;
    }

    public boolean addBoard(Board board, MultipartFile[] files) throws IOException {
        //게시물  insert
        int cnt = mapper.insert(board);

        for (MultipartFile file : files){
            if (file.getSize() > 0){
                // 파일 저장
                String folder = "C:\\study\\upload\\" + board.getId();
                File targetFolder = new File(folder);
                if (!targetFolder.exists()){
                    // 자기 폴더가 존재 하지 않으면 게시물 번호도 폴더를 만들어라.
                    targetFolder.mkdirs();
                }

                String path = "C:\\study\\upload\\" + board.getId() + "\\" + file.getOriginalFilename();
                File target = new File(path);
                file.transferTo(target);
                // db에 관련 정보 저장(insert)
                mapper.insertFileName(board.getId(), file.getOriginalFilename());

            }
        }

        return cnt == 1;
    }
}
