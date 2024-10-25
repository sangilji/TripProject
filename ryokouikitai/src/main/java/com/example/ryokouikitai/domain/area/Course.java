package com.example.ryokouikitai.domain.area;

import com.example.ryokouikitai.Trip.DTO.CourseRequestDTO;
import com.example.ryokouikitai.domain.board.BoardComment;
import com.example.ryokouikitai.domain.member.Member;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CourseAttraction> courseAttractions;

    private String title;
    private String content;
    private LocalDate startAt;
    private LocalDate endAt;
    private LocalDateTime createdAt;
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

    public void setAttractions(List<List<CourseAttraction>> collect) {
        List<CourseAttraction> newCourseAttraction = new ArrayList<>();
        for (List<CourseAttraction> attractions : collect) {
            newCourseAttraction.addAll(attractions);
        }
        courseAttractions= newCourseAttraction;
    }
}
