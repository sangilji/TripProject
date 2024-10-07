package com.example.ryokouikitai.dto.accompany;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
