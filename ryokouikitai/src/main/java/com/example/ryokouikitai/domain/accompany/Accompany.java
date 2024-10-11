package com.example.ryokouikitai.domain.accompany;

import com.example.ryokouikitai.domain.area.Area;
import com.example.ryokouikitai.domain.area.Category;
import com.example.ryokouikitai.domain.member.Member;
import java.util.List;

import com.example.ryokouikitai.dto.accompany.WriteForm;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "area_id")
    private Area area;

    @OneToMany(mappedBy = "accompany", fetch = FetchType.LAZY)
    private List<AccompanyComment> accompanyComments;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;
    private String title;
    private String content;
    private int memberCount;
    private int currentCount;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private LocalDateTime createdAt;
    private int likeCount;
    private int viewCount;


    public void updateViewCount() {
        this.viewCount+=1;
    }

    public void update(WriteForm writeForm, Area area, Category category) {
        this.area = area;
        this.category = category;
        this.content = writeForm.getContent();
        this.title = writeForm.getTitle();
        this.startAt = writeForm.getStartDate().atStartOfDay();
        this.endAt = writeForm.getEndDate().atStartOfDay();
    }

    public void updateLikeCount(int count) {
        likeCount += count;
    }
}
