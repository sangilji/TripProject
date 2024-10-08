package com.example.ryokouikitai.service.accompany;

import com.example.ryokouikitai.domain.accompany.Accompany;
import com.example.ryokouikitai.domain.accompany.AccompanyComment;
import com.example.ryokouikitai.domain.area.Area;
import com.example.ryokouikitai.domain.area.Category;
import com.example.ryokouikitai.domain.member.Member;
import com.example.ryokouikitai.dto.accompany.AccompanyDetailDto;
import com.example.ryokouikitai.dto.accompany.AccompanyResponseDto;
import com.example.ryokouikitai.dto.accompany.CommentDto;
import com.example.ryokouikitai.dto.accompany.WriteForm;
import com.example.ryokouikitai.repository.accompany.AccompanyCommentRepository;
import com.example.ryokouikitai.repository.accompany.AccompanyRepository;
import com.example.ryokouikitai.repository.area.AreaRepository;
import com.example.ryokouikitai.repository.area.ThemeRepository;
import com.example.ryokouikitai.repository.member.MemberRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccompanyService {

    private final MemberRepository memberRepository;
    private final AccompanyRepository accompanyRepository;
    private final ThemeRepository themeRepository;
    private final AreaRepository areaRepository;
    private final AccompanyCommentRepository accompanyCommentRepository;

    @Transactional
    public Accompany write(Integer id, WriteForm writeForm) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("회원정보 오류"));
        Area area = areaRepository.findByName(writeForm.getArea())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 지역"));
        Category Category = themeRepository.findById(Integer.valueOf(writeForm.getTheme()))
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 테마"));
        Accompany accompany = Accompany
                .builder()
                .member(member)
                .area(area)
                .category(Category)
                .content(writeForm.getContent())
                .currentCount(0)
                .memberCount(0)
                .title(writeForm.getTitle())
                .likeCount(0)
                .viewCount(0)
                .createdAt(LocalDateTime.now())
                .startAt(writeForm.getStartDate().atStartOfDay())
                .endAt(writeForm.getEndDate().atStartOfDay())
                .build();
        accompanyRepository.save(accompany);
        return accompany;
    }

    public Page<AccompanyResponseDto> getAll(Pageable pageable) {
        return accompanyRepository.getAccompanyWithMemberAndCommentCount(pageable);
    }

    @Transactional
    public AccompanyDetailDto getById(String accompanyId) {
        Accompany accompany = accompanyRepository.findByIdWithComment(Integer.valueOf(accompanyId));
        accompany.updateViewCount();
        return new AccompanyDetailDto(accompany);
    }

    @Transactional
    public CommentDto createComment(Integer id, String comment, String postId) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 멤버입니다."));
        Accompany accompany = accompanyRepository.getReferenceById(Integer.valueOf(postId));
        AccompanyComment accompanyComment = AccompanyComment.builder()
                .accompany(accompany)
                .member(member)
                .createdAt(LocalDateTime.now())
                .content(comment)
                .build();
        return accompanyCommentRepository.save(accompanyComment).commentDto();
    }

    public List<CommentDto> getCommentAll(String postId) {
        return accompanyCommentRepository.findAllByAccompanyId(
                Integer.parseInt(postId))
                .stream()
                .map(AccompanyComment::commentDto)
                .collect(Collectors.toList());

    }
}
