package com.example.ryokouikitai.domain.accompany;

import com.example.ryokouikitai.domain.area.Course;
import com.example.ryokouikitai.domain.member.Member;
import com.example.ryokouikitai.dto.accompany.CommentDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class AccompanyComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accompany_id")
    private Accompany accompany;
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

