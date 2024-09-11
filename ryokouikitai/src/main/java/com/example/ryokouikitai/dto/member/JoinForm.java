package com.example.ryokouikitai.dto.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class JoinForm {
    private String id;
    private String password;
    private String passwordCheck;
    private String nickname;
    private String email;
    private String theme;
}
