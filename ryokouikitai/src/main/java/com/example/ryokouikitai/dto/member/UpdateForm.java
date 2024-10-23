package com.example.ryokouikitai.dto.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class UpdateForm {
    private String nickname;
    private String password;
    private String theme;
}
