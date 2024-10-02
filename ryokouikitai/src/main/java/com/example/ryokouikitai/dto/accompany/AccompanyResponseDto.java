package com.example.ryokouikitai.dto.accompany;

import com.example.ryokouikitai.domain.accompany.Accompany;
import com.example.ryokouikitai.domain.accompany.AccompanyComment;
import com.example.ryokouikitai.domain.member.Member;
import lombok.*;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@AllArgsConstructor
public class AccompanyResponseDto {
    private Accompany accompany;
    private Long commentCount;
}
