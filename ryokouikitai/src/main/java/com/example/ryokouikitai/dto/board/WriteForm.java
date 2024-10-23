package com.example.ryokouikitai.dto.board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class WriteForm {
    private String boardName;
    private String theme;
    private String title;
    private String content;
    private Integer id;
}

