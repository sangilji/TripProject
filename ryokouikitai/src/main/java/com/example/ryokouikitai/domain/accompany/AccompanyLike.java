package com.example.ryokouikitai.domain.accompany;

import com.example.ryokouikitai.domain.area.Course;
import com.example.ryokouikitai.domain.member.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class AccompanyLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accompany_id")
    private Accompany accompany;
    private String url;

    @Column(name = "flag", nullable = false, columnDefinition = "TINYINT(1)")
    @NonNull
    private Boolean flag;

    public int changeLike() {
        flag = !flag;
        return flag ? 1: -1;
    }
}
