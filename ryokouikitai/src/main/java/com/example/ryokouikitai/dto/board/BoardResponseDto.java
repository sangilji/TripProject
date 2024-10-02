package com.example.ryokouikitai.dto.board;

import com.example.ryokouikitai.domain.board.Board;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@AllArgsConstructor

public class BoardResponseDto {
    private Board board;
    private Long commentCount;
}
