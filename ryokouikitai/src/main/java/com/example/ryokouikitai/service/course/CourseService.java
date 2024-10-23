package com.example.ryokouikitai.service.course;

import com.example.ryokouikitai.domain.area.Course;
import com.example.ryokouikitai.domain.member.Member;
import com.example.ryokouikitai.repository.course.CourseRepository;
import com.example.ryokouikitai.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final MemberRepository memberRepository;
    private final CourseRepository courseRepository;

    public Page<Course> getByMemberId(Integer id, Pageable pageable) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 멤버입니다. "));

        return courseRepository.findByMemberOrderByIdAsc(member, pageable);
    }
}
