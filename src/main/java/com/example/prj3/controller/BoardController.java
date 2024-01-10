package com.example.prj3.controller;

import com.example.prj3.domain.Board;
import com.example.prj3.mapper.BoardMapper;
import com.example.prj3.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/")
public class BoardController {

    @Autowired
    private BoardService service;

    // 경로 : http://localhost:8080
    // 경로 : http://localhost:8080/list
    // 게시물 목록
    // @RequestMapping(value = {"/", "list"}, method = RequestMethod.GET)
    @GetMapping({"/", "list"})
    public String list(Model model,
                       @RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "search", defaultValue = "")String search,
                       @RequestParam(value = "type", required = false) String type){
        // 1. request param 수집/가공
        // 2. business login 처리
        // List<Board> list = service.listBoard(); // 페이지 처리 전
        Map<String, Object> result = service.listBoard(page, search, type); // 페이지 처리

        // 3. add attribute 위에 두개를 밑에 한개로 대신 쓸수 있다.
//        model.addAttribute("boardList", result.get("boardList"));
//        model.addAttribute("pageInfo", result.get("pageInfo"));
        model.addAllAttributes(result);

        // 4. forward/redirect
        return "list";
    }

    @GetMapping("/id/{id}")
    public String board(@PathVariable("id") Integer id, Model model) {
        // 1. request param
        // 2. business logic
        Board board = service.getBoard(id);
        // 3. add attribute
        model.addAttribute("board", board);
        // 4. forward/redirect
        return "get";
    }

    @GetMapping("/modify/{id}")
    public String modifyForm(@PathVariable("id") Integer id, Model model){

        model.addAttribute("board", service.getBoard(id));
        return "modify";
    }

    @PostMapping("/modify/{id}")
    public String modifyProcess(Board board, RedirectAttributes rttr){

        boolean ok = service.modify(board);

        if (ok) {
            // 해당 게시물 보기로 리디렉션
//            rttr.addAttribute("success", "success");
            rttr.addFlashAttribute("message", board.getId() + " 번 게시물이 수정되었소");
            return "redirect:/id/" + board.getId();
        } else {
            // 수정 form 으로 리디렉션
//            rttr.addAttribute("fail", "fail");
            rttr.addFlashAttribute("message", board.getId() + "번 게시물이 수정되지 않았소");
            return "redirect:/modify" + board.getId();
        }
    }

    @PostMapping("remove")
    public String remove(Integer id, RedirectAttributes rttr) {
        boolean ok = service.remove(id);
        if (ok) {
            // quert string에 추가
//            rttr.addAttribute("success", "remove");
            // 모델에 추가
            rttr.addFlashAttribute("message",id + "번 게시물이 삭제되었소.");
            return "redirect:/list";
        } else {
            rttr.addAttribute("fail", "fail");
            return "redirect:/id/" + id;
        }
    }

    @GetMapping("add")
    public void addForm(){
        // 게시물 작성 form (view)로 포워드
    }

    @PostMapping("add")
    public String addProcess(Board board, RedirectAttributes rttr){
        // 새 게시물  db에 추가
        // 1.
        // 2.
        boolean ok = service.addBoard(board);
        // 3.
        // 4.
        if (ok) {
            rttr.addFlashAttribute("message", board.getId() + "번 게시물이 등록되었소");
            return "redirect:/id/" + board.getId();
//            등록이 잘되면 내가 쓴글로 다시 가고싶다.
        } else {
            rttr.addFlashAttribute("message", "게시물 등록중 문제가 발생하였소");
            rttr.addFlashAttribute("board", board);
            return "redirect:/add";
        }
    }
}
