package com.example.ryokouikitai.domain.accompany;

import com.example.ryokouikitai.domain.area.Area;
import com.example.ryokouikitai.domain.area.Category;
import com.example.ryokouikitai.domain.member.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Accompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id")
    private Area area;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    private String title;
    private String content;
    private int memberCount;
    private int currentCount;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private int likeCount;
    private int viewCount;


}
