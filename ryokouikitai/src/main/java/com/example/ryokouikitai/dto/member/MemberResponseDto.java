package com.example.ryokouikitai.dto.member;

import com.example.ryokouikitai.domain.accompany.Accompany;
import com.example.ryokouikitai.domain.board.Board;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@ToString

public class MemberResponseDto {
    private Board board;
    private Accompany accompany;
}
