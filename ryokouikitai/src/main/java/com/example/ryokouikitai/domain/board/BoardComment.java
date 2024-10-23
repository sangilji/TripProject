package com.example.ryokouikitai.domain.board;

import com.example.ryokouikitai.domain.member.Member;
import com.example.ryokouikitai.dto.board.CommentDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class BoardComment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER) // 외래키에 접근할 수 있도록 -> 조인할 필요가 없음
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY) // 외래키 접근 불가 -> 조인
    @JoinColumn(name = "board_id")
    private Board board;
    private String content;
    private LocalDateTime createdAt;

    public CommentDto commentDto() {
        return CommentDto.builder()
                .id(id)
                .nickname(member.getNickname())
                .content(content)
                .createdAt(createdAt)
                .build();
    }

}
