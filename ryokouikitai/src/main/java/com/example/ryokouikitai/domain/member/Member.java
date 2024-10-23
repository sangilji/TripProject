package com.example.ryokouikitai.domain.member;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String boardBar;

    private String userId;

    @NonNull
    private String nickname;
    @NonNull
    private String password;
    @NonNull
    private String email;

    private String image;

    @Builder.Default
    private Double score = 0.0;

    private String theme;

    @Builder.Default
    private Integer point=0;

    private String profile;

    public MemberInfo toMemberInfo() {
        return MemberInfo.builder()
                .id(id)
                .userId(userId)
                .nickname(nickname)
                .theme(theme)
                .point(point)
                .profile(profile)
                .build();
    }

    public void updateProfile(String profile) {
//        DB 저장
        this.profile=profile;
    }

    public void updateInfo(String nickname, String password, String theme) {
        this.nickname = nickname;
        this.password = password;
        this.theme=theme;
    }
}
