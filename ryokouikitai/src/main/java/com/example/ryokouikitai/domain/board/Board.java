package com.example.ryokouikitai.domain.board;

import com.example.ryokouikitai.domain.area.Category;
import com.example.ryokouikitai.domain.member.Member;
import com.example.ryokouikitai.dto.board.WriteForm;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category theme;

    @Column(name = "board_menu")
    private String boardMenu;

    // 조회할 수 있도록 만드는 기능
    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    private List<BoardComment> boardComments;

    private String title;

    @Column(length = 2048)
    private String content;
    private int likeCount;
    private int viewCount;
    private LocalDateTime createdAt;
    @Column(name = "flag", nullable = false, columnDefinition = "TINYINT(1)")
    @NonNull
    private boolean flag;

    public void updateViewCount() {
        this.viewCount += 1;
    }

    public void update(WriteForm writeForm, Category category) {
        this.theme = category;
        this.boardMenu = writeForm.getBoardName();
        this.title = writeForm.getTitle();
        this.content = writeForm.getContent();
    }

    public void updateLikeCount(int count) {
        likeCount += count;
    }
}
