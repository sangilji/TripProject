package com.example.ryokouikitai.dto.board;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@AllArgsConstructor
@Builder

public class CommentDto {
    private Integer id;
    private String content;
    private LocalDateTime createdAt;
    private String nickname;
}
