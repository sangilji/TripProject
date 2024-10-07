package com.example.ryokouikitai.dto.accompany;

import com.example.ryokouikitai.domain.accompany.Accompany;
import com.example.ryokouikitai.domain.accompany.AccompanyComment;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class AccompanyDetailDto {

    private Accompany accompany;

    private List<AccompanyComment> comments;

    public AccompanyDetailDto(Accompany accompany) {
        this.accompany = accompany;
        this.comments = accompany.getAccompanyComments();
        this.comments.sort((o1, o2) -> o2.getCreatedAt().compareTo(o1.getCreatedAt()));
    }
}
