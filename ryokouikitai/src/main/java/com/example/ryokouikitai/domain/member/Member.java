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
    public MemberInfo toMemberInfo() {
        return MemberInfo.builder()
                .id(id)
                .userId(userId)
                .nickname(nickname)
                .theme(theme)
                .build();
    }
}
