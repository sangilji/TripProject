package com.example.ryokouikitai.domain.board;

import com.example.ryokouikitai.domain.area.Area;
import com.example.ryokouikitai.domain.area.Category;
import com.example.ryokouikitai.domain.member.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category theme;

    private String title;
    private String content;
    private int likeCount;
    private int viewCount;
    private LocalDateTime createdAt;
    @Column(name = "flag", nullable = false, columnDefinition = "TINYINT(1)")
    @NonNull
    private boolean flag;
}
