package com.example.ryokouikitai.Trip;

import com.example.ryokouikitai.domain.area.Course;
import com.example.ryokouikitai.domain.area.CourseAttraction;
import com.example.ryokouikitai.domain.member.Member;
import com.example.ryokouikitai.repository.member.MemberRepository;
import com.example.ryokouikitai.Trip.DTO.CourseAttractionDTO;
import com.example.ryokouikitai.Trip.DTO.CourseRequestDTO;
import com.example.ryokouikitai.Trip.Repository.CourseAttractionRepository;
import com.example.ryokouikitai.Trip.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseAttractionRepository courseAttractionRepository;

    @Autowired
    private MemberRepository memberRepository;

    // 코스 및 장소 저장 메서드
    @Transactional
    public void createCourseWithAttractions(CourseRequestDTO requestDTO, Integer memberId) {

        // requestDTO로부터 memberId를 동적으로 가져옴
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        Course course = Course.builder()
                .title(requestDTO.getTitle())
                .content(requestDTO.getContent())
                .startAt(requestDTO.getStartAt())
                .endAt(requestDTO.getEndAt())
                .flag(false)
                .member(member)
                .likeCount(0)
                .viewCount(0)
                .build();

        // 코스 저장
        courseRepository.save(course);

        // 일차별로 장소 저장
        for (CourseAttractionDTO attractionDTO : requestDTO.getAttractions()) {
            CourseAttraction courseAttraction = CourseAttraction.builder()
                    .course(course)
                    .attraction(attractionDTO.getAttraction())
                    .day(attractionDTO.getDay())
                    .orders(attractionDTO.getOrders())
                    .build();
            courseAttractionRepository.save(courseAttraction);
        }
    }

    // 코스 삭제 메서드
    public void deleteCourse(Integer courseId) {
        courseAttractionRepository.deleteByCourseId(courseId);
        courseRepository.deleteById(courseId);
    }

    // 코스 업데이트 메서드 (필요 시 추가)
    @Transactional
    public void updateCourse(Integer id, CourseRequestDTO requestDTO) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        course.update(requestDTO);
    }
}
