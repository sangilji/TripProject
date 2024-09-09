package com.example.ryokouikitai.domian.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class LoginForm {
    private String id;
    private String password;
}
