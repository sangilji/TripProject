package com.example.ryokouikitai.domain.area;

import com.example.ryokouikitai.Trip.DTO.CourseRequestDTO;
import com.example.ryokouikitai.domain.member.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String title;
    private String content;
    private LocalDate startAt;
    private LocalDate endAt;
    @Column(name = "flag", nullable = false, columnDefinition = "TINYINT(1)")
    @NonNull
    private Boolean flag;
    private int likeCount;
    private int viewCount;


    public void update(CourseRequestDTO requestDTO) {
        this.startAt = requestDTO.getStartAt();
        this.endAt = requestDTO.getEndAt();
        this.flag = requestDTO.getFlag();
        this.title =requestDTO.getTitle();
        this.content = requestDTO.getContent();


    }
}
