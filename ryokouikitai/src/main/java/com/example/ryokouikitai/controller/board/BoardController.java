package com.example.ryokouikitai.controller.board;

import com.example.ryokouikitai.domain.board.Board;
import com.example.ryokouikitai.domain.member.MemberInfo;
import com.example.ryokouikitai.dto.board.BoardDetailDto;
import com.example.ryokouikitai.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

// 페이지 이동 return을 통해서 이동
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
@Slf4j
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/tip")
    public String getBoard1(Model model, @PageableDefault(size=4) Pageable pageable) {
        Page<Board> boardList = boardService.getAllByBoardName(pageable, "tip");
        model.addAttribute("boardList", boardList.getContent());
        model.addAttribute("page", boardList);
        return "board/board1";
    }

    @GetMapping("/plan")
    public String getBoard2(Model model, @PageableDefault(size = 4) Pageable pageable) {
        Page<Board> boardList = boardService.getAllByBoardName(pageable, "plan");
        model.addAttribute("boardList", boardList.getContent());
        model.addAttribute("page", boardList);
        return "board/board2";
    }

    @GetMapping("/rank")
    public String getBoard3() {
        return "board/board3";
    }

    @GetMapping("tip/{board-id}")
    public String getBoard1Detail(HttpSession session, @PathVariable("board-id") String boardId, Model model) {
        MemberInfo memberInfo = (MemberInfo) session.getAttribute("memberInfo");
        BoardDetailDto boardDetailDto = boardService.getById(memberInfo, boardId);
        model.addAttribute("boardDto", boardDetailDto);
        return "board/board1detail";
    }

    @GetMapping("plan/{board-id}")
    public String getBoard2Detail(@PathVariable("board-id") String boardId) {
        return "board/board2detail";
    }

    @GetMapping("/write")
    public String getWriteBoard() {
        return "board/write";
    }

    @GetMapping("/rewrite/{board-id}")
    public String getRewriteBoard(HttpSession session, @PathVariable("board-id") String boardId, Model model) {
        MemberInfo memberInfo = (MemberInfo) session.getAttribute("memberInfo");
        Board board = boardService.getById(memberInfo, boardId).getBoard();
        model.addAttribute("board", board);
        return "board/rewrite";
    }

    @GetMapping("/search")
    public String search(@RequestParam String theme, Model model, @PageableDefault(size = 4) Pageable pageable) {
        log.info("{}", theme);
//        boardService.getByTheme(theme, pageable);
        return "board/board2 :: search";
    }
}
