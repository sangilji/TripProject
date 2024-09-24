package com.example.ryokouikitai.domain.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@AllArgsConstructor
@Builder
public class MemberInfo {
    private Integer id;
    private String userId;
    private String nickname;
    private String theme;

}
