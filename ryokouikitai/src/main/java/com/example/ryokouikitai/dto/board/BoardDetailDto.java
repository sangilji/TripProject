package com.example.ryokouikitai.dto.board;

import com.example.ryokouikitai.domain.board.Board;
import com.example.ryokouikitai.domain.board.BoardComment;
import lombok.*;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class BoardDetailDto {
    private Board board;

    private List<BoardComment> comments;

    private Boolean flag;


    public BoardDetailDto(Board board) {
        this.board = board;
        this.comments = board.getBoardComments();
        this.comments.sort(((o1, o2) -> o2.getCreatedAt().compareTo(o1.getCreatedAt())));
        this.flag=false;
    }

    public BoardDetailDto(boolean b, Board board) {
        this.board = board;
        this.comments = board.getBoardComments();
        this.comments.sort(((o1, o2) -> o2.getCreatedAt().compareTo(o1.getCreatedAt())));
        this.flag=b;
    }
}
